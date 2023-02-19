package com.example.h2osynchro.controleur;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.h2osynchro.R;
import com.example.h2osynchro.modele.Critere;
import com.example.h2osynchro.modele.CritereDAO;

public class CritereActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_critere);
		this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		LinearLayout listeCritereLL = (LinearLayout) findViewById(R.id.listeCritereLL);
		CritereDAO critereAcces = new CritereDAO(this);
		ArrayList<Critere> listeCriteres = critereAcces.getCriteres();
		int largCol1 = 100;
		int largCol2 = 100;
		int largCol3 = 100;
				
		LinearLayout ligne = new LinearLayout(this); 
		TextView idC = new TextView(this);
		idC.setWidth(largCol1);
		idC.setText("idC");
		ligne.addView(idC);
		
		TextView libelleC = new TextView(this);
		libelleC.setWidth(largCol2);
		libelleC.setText("libelleC");
		ligne.addView(libelleC);
		
		TextView uniteC = new TextView(this);
		uniteC.setWidth(largCol3);
		uniteC.setText("uniteC");
		ligne.addView(uniteC);
		listeCritereLL.addView(ligne);
		
		for(int i=0;i<listeCriteres.size();i++){
			ligne = new LinearLayout(this); 
			idC = new TextView(this);
			idC.setWidth(largCol1);
			idC.setText(Long.toString(listeCriteres.get(i).getIdC()));
			ligne.addView(idC);
			
			libelleC = new TextView(this);
			libelleC.setWidth(largCol2);
			libelleC.setText(listeCriteres.get(i).getLibelleC());
			ligne.addView(libelleC);
			
			uniteC = new TextView(this);
			uniteC.setWidth(largCol3);
			uniteC.setText(listeCriteres.get(i).getUniteC());
			ligne.addView(uniteC);
			listeCritereLL.addView(ligne);
		}
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
