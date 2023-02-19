package com.example.h2osynchro.modele;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StationDAO {
	private static String base = "BDh2o";
	private static int version = 1;
	BD_SQLiteOpenHelper accesBD;
	
	public StationDAO(Context ct){
		accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);	
	}
	
	
	public long addStation(Station unStation){
		long ret;
		SQLiteDatabase bd = accesBD.getWritableDatabase();
		
		ContentValues value = new ContentValues();
		value.put("idS", unStation.getIdS());
		value.put("nomS", unStation.getNomS());
		ret = bd.insert("station", null, value);
		
		return ret;
	}
	
	public void updStation(Station uneStation){
		SQLiteDatabase bd = accesBD.getWritableDatabase();
		bd.execSQL("update station " +
				"set nomS='"+uneStation.getNomS()+"' " +
				"where idS="+uneStation.getIdS()+";");
	}
		
	public Station getStation(long idS){
		Station laStation = null;
		Cursor curseur;
		curseur = accesBD.getReadableDatabase().rawQuery("select * from station where idS="+idS+";",null);
		if (curseur.getCount() > 0) {
			curseur.moveToFirst();
			laStation = new Station(idS,curseur.getString(1));
		}
		return laStation;
	}
	
	public ArrayList<Station> getStations(){
		Cursor curseur;
		String req = "select * from station";
		curseur = accesBD.getReadableDatabase().rawQuery(req,null);
		return cursorToStationArrayList(curseur);
	}
	
	
	private ArrayList<Station> cursorToStationArrayList(Cursor curseur){
		ArrayList<Station> listeStation = new ArrayList<Station>();
		long idS;
		String nomS;
		
		curseur.moveToFirst();
		while (!curseur.isAfterLast()){
			idS = curseur.getLong(0);
			nomS = curseur.getString(1);
			listeStation.add(new Station(idS,nomS));
			curseur.moveToNext();
		}
		
		return listeStation;
	}
}
