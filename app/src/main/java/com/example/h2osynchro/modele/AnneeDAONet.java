package com.example.h2osynchro.modele;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.h2osynchro.utilitaire.AccesHTTP;
import com.example.h2osynchro.utilitaire.EventAsyncAnnee;
import com.example.h2osynchro.utilitaire.Parametres;

public abstract class  AnneeDAONet implements EventAsyncAnnee {
	
	public AnneeDAONet(){
	}
	
	public void addAnnee(Annee uneAnnee){
		AccesHTTP requeteHttp = new AccesHTTP(){
			@Override
			protected void onPostExecute(Long result) {
				Log.d("log","onPostExecute CritereDAONet");
				onTacheTerminee(this.ret);
			}
		}; 
		
		requeteHttp.addParam("numA", ""+uneAnnee.getNumA());
		requeteHttp.addParam("idS", ""+uneAnnee.getIdS());
		requeteHttp.addParam("introA", uneAnnee.getIntroA());
		requeteHttp.addParam("conclusionA",uneAnnee.getConclusionA());
		
		requeteHttp.execute("http://"+ Parametres.serveur+Parametres.chemin+"addAnnee.php");

	}	
}
