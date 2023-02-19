package com.example.h2osynchro.modele;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CritereDAO {
	private static String base = "BDh2o";
	private static int version = 1;
	BD_SQLiteOpenHelper accesBD;
	
	public CritereDAO(Context ct){
		accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);	
	}
	
	
	public long addCritere(Critere unCritere){
		long ret;
		SQLiteDatabase bd = accesBD.getWritableDatabase();
		
		ContentValues value = new ContentValues();
		value.put("idC", unCritere.getIdC());
		value.put("libelleC", unCritere.getLibelleC());
		value.put("uniteC",unCritere.getUniteC());
		ret = bd.insert("critere", null, value);
		
		return ret;
	}

	public void updCritere(Critere unCritere){
		SQLiteDatabase bd = accesBD.getWritableDatabase();
		bd.execSQL("update critere " +
				"set libelleC='"+unCritere.getLibelleC()+"' " +
				", uniteC='"+unCritere.getUniteC()+"' " +
				"where idC="+unCritere.getIdC()+";");
	}

		
	public Critere getCritere(long idC){
		Critere leCritere = null;
		Cursor curseur;
		curseur = accesBD.getReadableDatabase().rawQuery("select * from critere where idC="+idC+";",null);
		if (curseur.getCount() > 0) {
			curseur.moveToFirst();
			leCritere = new Critere(idC,curseur.getString(1), curseur.getString(2));
		}
		return leCritere;
	}
	
	public ArrayList<Critere> getCriteres(){
		Cursor curseur;
		String req = "select * from critere";
		curseur = accesBD.getReadableDatabase().rawQuery(req,null);
		return cursorToCritereArrayList(curseur);
	}
	
	
	private ArrayList<Critere> cursorToCritereArrayList(Cursor curseur){
		ArrayList<Critere> listeCritere = new ArrayList<Critere>();
		long idC;
		String libelleC;
		String uniteC;
		
		curseur.moveToFirst();
		while (!curseur.isAfterLast()){
			idC = curseur.getLong(0);
			libelleC = curseur.getString(1);
			uniteC = curseur.getString(2);
			listeCritere.add(new Critere(idC,libelleC,uniteC));
			curseur.moveToNext();
		}
		
		return listeCritere;
	}
	
	
}
