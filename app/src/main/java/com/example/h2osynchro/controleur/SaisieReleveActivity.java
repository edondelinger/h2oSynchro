package com.example.h2osynchro.controleur;

import java.util.ArrayList;
import java.util.Calendar;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.h2osynchro.R;
import com.example.h2osynchro.modele.Critere;
import com.example.h2osynchro.modele.CritereDAO;
import com.example.h2osynchro.modele.Relever;
import com.example.h2osynchro.modele.ReleverDAO;
import com.example.h2osynchro.modele.Station;
import com.example.h2osynchro.modele.StationDAO;

public class SaisieReleveActivity extends AppCompatActivity {
	private Spinner spinCriteres;
	private Button btnEnregistrerReleve;
	private ArrayList<Critere> listeCriteres;
	
	private int annee;
	private int mois;
	private long idStation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saisie_releve);
		this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		spinCriteres = (Spinner) findViewById(R.id.spinCritere);
		btnEnregistrerReleve = (Button) findViewById(R.id.btnEnregistrerReleve);
		
		CritereDAO criteresAcces = new CritereDAO(this);
		listeCriteres = criteresAcces.getCriteres();
		ArrayAdapter<String> spinCriteresAdapter = new ArrayAdapter<String>(this.getBaseContext(),android.R.layout.simple_spinner_item);
						
		for(int i=0;i<listeCriteres.size();i++){
			spinCriteresAdapter.add(listeCriteres.get(i).getLibelleC());
		}
		spinCriteres.setAdapter(spinCriteresAdapter);
		Log.d("log","test");
		
		spinCriteres.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Log.d("log",Integer.toString(arg2)+" "+listeCriteres.get(arg2).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnEnregistrerReleve.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("log",Integer.toString(spinCriteres.getSelectedItemPosition()));
				
				// enregistrer le releve dans la base de donnees
				
				// recuperation des donnees saisies, selectionnees, et transmises a l'activity
				EditText txtEntree = (EditText) findViewById(R.id.txtEntree);
				EditText txtSortie = (EditText) findViewById(R.id.txtSortie);
				long idC = listeCriteres.get(spinCriteres.getSelectedItemPosition()).getIdC();

				// recuperation du jours courant
				Calendar cal = Calendar.getInstance();
				Integer jour = cal.get(Calendar.DAY_OF_MONTH);
				
				// creation de l'objet de type Relever pour permettre l'insertion dans la base a l'aide de la classe DAO
				Relever unRelever = new Relever(mois,annee,idStation,idC,jour,Integer.valueOf(txtEntree.getText().toString()),Integer.valueOf(txtSortie.getText().toString()));
				Log.d("unRelever",unRelever.toString());
				
				ReleverDAO releverAcces = new ReleverDAO(SaisieReleveActivity.this);
				releverAcces.addRelever(unRelever);
				
				Log.d("releves enregistres",releverAcces.getRelever().toString());
			}
		});
		
		// recuperation des donnees envoyees a l'activity
		Bundle bundleRecu = this.getIntent().getExtras();
		annee = bundleRecu.getInt("annee");
		mois = bundleRecu.getInt("mois");
		idStation = bundleRecu.getLong("station");
		Log.d("recup param","annee : "+annee);
		Log.d("recup param","mois : "+mois);
		Log.d("recup param","station : "+idStation);
		
		// affichage dans les champs en haut de l'activity
		TextView lblAnnee = (TextView) findViewById(R.id.lblAnnee);
		TextView lblMois = (TextView) findViewById(R.id.lblMois);
		TextView lblStation = (TextView) findViewById(R.id.lblStation);

		lblAnnee.setText(String.valueOf(annee));
		lblMois.setText(String.valueOf(mois));
		
		StationDAO StationAcces = new StationDAO(this);
		Station laStationSelect = StationAcces.getStation(idStation);
		lblStation.setText(laStationSelect.getNomS());
		
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        finish();
	        break;
	    }
	    return super.onOptionsItemSelected(item);
	}


}
