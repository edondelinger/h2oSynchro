package com.example.h2osynchro.controleur;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.h2osynchro.R;
import com.example.h2osynchro.modele.Annee;
import com.example.h2osynchro.modele.AnneeDAONet;
import com.example.h2osynchro.modele.Mois;
import com.example.h2osynchro.modele.MoisDAO;
import com.example.h2osynchro.modele.MoisDAONet;
import com.example.h2osynchro.modele.Station;
import com.example.h2osynchro.modele.StationDAONet;

public class SynchroActivity extends AppCompatActivity {
	private Button btnSyncDesc;
	private Button btnSyncAsc;
	private TextView tvLogSyncAsc;
	private TextView tvLogSyncDesc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_synchro);
		this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		btnSyncDesc = (Button) findViewById(R.id.btnSyncDesc);
		btnSyncAsc = (Button) findViewById(R.id.btnSyncAsc);
		tvLogSyncAsc = (TextView) findViewById(R.id.tvLogSyncAsc);
		tvLogSyncDesc = (TextView) findViewById(R.id.tvLogSyncDesc);
		
		btnSyncDesc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Effectuer la synchronisation descendante puis indiquer a l'utilisateur l'etat d'avancement
				// recuperation des stations
				StationDAONet accesStationNet = new StationDAONet(){

					@Override
					public void onTacheTerminee(String resultat) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onTacheTerminee(ArrayList<Station> resultat) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onTacheTerminee(Station resultat) {
						// TODO Auto-generated method stub
						
					}
				};
				accesStationNet.getStations();
				
				
				
				// recuperation des criteres 
			
			}
		});
		
		btnSyncAsc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Effectuer la synchronisation montante puis indiquer a l'utilisateur l'etat d'avancement
				// envoi des annees 
				AnneeDAONet accesAnneeNet = new AnneeDAONet(){

					@Override
					public void onTacheTerminee(String resultat) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onTacheTerminee(ArrayList<Annee> resultat) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onTacheTerminee(Annee resultat) {
						// TODO Auto-generated method stub
						
					}	
				};

				
				// envoi des mois
				MoisDAONet accesMoisNet = new MoisDAONet(){

					@Override
					public void onTacheTerminee(String resultat) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onTacheTerminee(ArrayList<Mois> resultat) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onTacheTerminee(Mois resultat) {
						// TODO Auto-generated method stub
						
					}	
				};
				
				MoisDAO accesMois = new MoisDAO(SynchroActivity.this);
				ArrayList<Mois> listeMois = accesMois.getMois();
				
				for(int i=0;i<listeMois.size();i++){
					Log.d("sync asc mois",listeMois.get(i).toString());
					accesMoisNet.addMois(listeMois.get(i));
				}

				
				// envoi des releves

				
			}
		});

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
