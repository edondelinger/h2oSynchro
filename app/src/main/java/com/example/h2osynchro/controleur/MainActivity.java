package com.example.h2osynchro.controleur;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import androidx.appcompat.app.AppCompatActivity;

import com.example.h2osynchro.R;
import com.example.h2osynchro.modele.Annee;
import com.example.h2osynchro.modele.AnneeDAO;
import com.example.h2osynchro.modele.Critere;
import com.example.h2osynchro.modele.CritereDAO;
import com.example.h2osynchro.modele.Mois;
import com.example.h2osynchro.modele.MoisDAO;
import com.example.h2osynchro.modele.Relever;
import com.example.h2osynchro.modele.ReleverDAO;
import com.example.h2osynchro.modele.Station;
import com.example.h2osynchro.modele.StationDAO;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
	private Spinner spinStations;
	private ArrayList<Station> listeStations;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		Button btnSaisieReleve = (Button) findViewById(R.id.btnSaisieReleve);
		btnSaisieReleve.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView txtAnnee = (TextView) findViewById(R.id.txtAnnee);
				TextView txtMois = (TextView) findViewById(R.id.txtMois);
				spinStations = (Spinner) findViewById(R.id.spinStation);

				
				Intent intent = new Intent(MainActivity.this, SaisieReleveActivity.class);
				
				Bundle bundleATransmettre = new Bundle();
				bundleATransmettre.putInt("annee",Integer.valueOf(txtAnnee.getText().toString()));
				bundleATransmettre.putInt("mois",Integer.valueOf(txtMois.getText().toString()));
				
				StationDAO StationAcces = new StationDAO(MainActivity.this);
				listeStations = StationAcces.getStations();
				
				Log.d("station",""+spinStations.getSelectedItemPosition());
				Log.d("station",listeStations.get(spinStations.getSelectedItemPosition()).getNomS());
				bundleATransmettre.putLong("station",listeStations.get(spinStations.getSelectedItemPosition()).getIdS());
				intent.putExtras(bundleATransmettre );
				
				
				startActivity(intent);
			}
		});
		
		// pre-saisie des champs mois et annee
		TextView txtAnnee = (TextView) findViewById(R.id.txtAnnee);
		TextView txtMois = (TextView) findViewById(R.id.txtMois);
		Calendar cal = Calendar.getInstance();
		Integer annee = cal.get(Calendar.YEAR);
		Integer mois = cal.get(Calendar.MONTH)+1;
		
		txtAnnee.setText(annee.toString());
		txtMois.setText(mois.toString());
		
		
		listeStations = (new StationDAO(this)).getStations();
		Log.d("test station", listeStations.toString());
		
		// liste deroulante de stations
		spinStations = (Spinner) findViewById(R.id.spinStation);
		StationDAO StationAcces = new StationDAO(this);
		listeStations = StationAcces.getStations();
		ArrayAdapter<String> spinStationsAdapter = new ArrayAdapter<String>(this.getBaseContext(),android.R.layout.simple_spinner_item);
						
		for(int i=0;i<listeStations.size();i++){
			spinStationsAdapter.add(listeStations.get(i).getNomS());
		}
		spinStations.setAdapter(spinStationsAdapter);
		
		spinStations.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Log.d("log",Integer.toString(arg2)+" "+listeStations.get(arg2).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// test classes Annee, Mois, Releve, Station, Critere
		// creation de l'annee en cours si elle n'existe pas deja dans la base
		AnneeDAO anneeAcces = new AnneeDAO(this);
		
		Annee uneAnnee = anneeAcces.getAnnee(annee, listeStations.get(0).getIdS());
		Log.d("classe Annee", ""+uneAnnee);		
		if (uneAnnee == null){
			Annee anneeAdd = new Annee(annee,listeStations.get(0).getIdS(),"introduction","conclusion");
			Log.d("classe Annee", ""+anneeAdd);
			
			anneeAcces.addAnnee(anneeAdd);
			uneAnnee = anneeAcces.getAnnee(annee, listeStations.get(0).getIdS());
			Log.d("classe Annee", ""+uneAnnee);
		}
		
		
		// creation du mois en cours si il n'existe pas deja dans la base
		MoisDAO moisAcces = new MoisDAO(this);
		Mois unMois = moisAcces.getMois(mois, annee, listeStations.get(0).getIdS());
		Log.d("classe Mois", ""+unMois);		
		
		if (unMois == null){
			Mois moisAdd = new Mois(mois, annee, listeStations.get(0).getIdS(),"remarques");
			Log.d("classe Mois", ""+moisAdd);	

			moisAcces.addMois(moisAdd);
			unMois = moisAcces.getMois(mois, annee, listeStations.get(0).getIdS());
			Log.d("classe Mois", ""+unMois);	
		}
		
		// creation d'un releve si il n'existe pas deja dans la base
		ReleverDAO releverAcces = new ReleverDAO(this);
		Relever unRelever = releverAcces.getRelever(mois, annee, listeStations.get(0).getIdS(),new CritereDAO(this).getCriteres().get(0).getIdC(),1 );
		Log.d("classe Relever", ""+unRelever);		
		
		if (unRelever == null){
			Relever releveAdd = new Relever(mois, annee, listeStations.get(0).getIdS(),new CritereDAO(this).getCriteres().get(0).getIdC(),1,100,200);
						
			Log.d("classe Relever", ""+releveAdd);	

			releverAcces.addRelever(releveAdd);
			unRelever = releverAcces.getRelever(mois, annee, listeStations.get(0).getIdS(),new CritereDAO(this).getCriteres().get(0).getIdC(),1 );
			Log.d("classe Relever", ""+unRelever);	
		}
		
		// creation d'une station si elle n'existe pas deja dans la base
		StationDAO stationAcces = new StationDAO(this);		
		Station uneStation = stationAcces.getStation(2);
		Log.d("classe Station", ""+uneStation);		
		
		if (uneStation == null){
			Station stationAdd = new Station(listeStations.get(listeStations.size()-1).getIdS()+1,"station des vignes du sud-ouest");
						
			Log.d("classe Station", ""+stationAdd);	

			stationAcces.addStation(stationAdd);
			ArrayList<Station> lesStations = stationAcces.getStations();
			Log.d("classe Station", ""+lesStations);	
		}
		
		// creation d'un critere si il n'existe pas deja dans la base
		CritereDAO critereAcces = new CritereDAO(this);
		Critere unCritere = critereAcces.getCritere(4);
		Log.d("classe Critere", ""+unCritere);		
		
		if (unCritere == null){
			Critere critereAdd = new Critere(critereAcces.getCriteres().get(critereAcces.getCriteres().size()-1).getIdC()+1,"haute densite","kg/l");
						
			Log.d("classe Critere", ""+critereAdd);	

			critereAcces.addCritere(critereAdd);
			ArrayList<Critere> lesCriteres = critereAcces.getCriteres();
			Log.d("classe Critere", ""+lesCriteres);	
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()){
		case R.id.afficherCriteres :
			intent = new Intent(this, CritereActivity.class);
			startActivity(intent);
			break;
		case R.id.synchronisationBase :
			intent = new Intent(this, SynchroActivity.class);
			startActivity(intent);
			break;
		}
		return true;
	}
	
}
