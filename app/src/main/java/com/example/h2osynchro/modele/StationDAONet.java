package com.example.h2osynchro.modele;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.util.Log;

import com.example.h2osynchro.utilitaire.AccesHTTP;
import com.example.h2osynchro.utilitaire.EventAsyncStation;
import com.example.h2osynchro.utilitaire.Parametres;

public abstract class  StationDAONet implements EventAsyncStation {
	
	public StationDAONet(){
	}

	public void getStations(){
		AccesHTTP requeteHttp = new AccesHTTP(){
			@Override
			protected void onPostExecute(Long result) {
				Log.d("log","onPostExecute StationDAONet");
				onTacheTerminee(jsonStringToStationArrayList(this.ret));
			}
		}; 
		requeteHttp.execute("http://"+ Parametres.serveur+Parametres.chemin+"getStations.php");
	}
		
	private ArrayList<Station> jsonStringToStationArrayList(String jsonString){
		Log.d("log","conversion jsonToStationArray : "+jsonString);

		ArrayList<Station> listeStation = new ArrayList<Station>();
		long idS;
		String nomS;
			
		try {
			JSONArray tabJson = new JSONArray(jsonString);
			for(int i=0;i<tabJson.length();i++){
				idS = Long.parseLong(tabJson.getJSONObject(i).getString("idS"));
				nomS = tabJson.getJSONObject(i).getString("nomS");
				
				listeStation.add(new Station(idS, nomS));
			}
		}
		catch (JSONException e){
			Log.d("log","pb decodage JSON");
		}
		return listeStation;
	}
	
}
