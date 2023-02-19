package com.example.h2osynchro.modele;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MoisDAO {
	private static String base = "BDh2o";
	private static int version = 1;
	BD_SQLiteOpenHelper accesBD;
	
	public MoisDAO(Context ct){
		accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);	
	}
	
	
	public long addMois(Mois unMois){
		long ret;
		SQLiteDatabase bd = accesBD.getWritableDatabase();

		ContentValues value = new ContentValues();
		value.put("numM", unMois.getNumM());
		value.put("numA", unMois.getNumA());
		value.put("idS", unMois.getIdS());
		value.put("remarquesM", unMois.getRemarquesM());
		ret = bd.insert("mois", null, value);
		
		return ret;
	}
	
		
	public Mois getMois(long numM, long numA, long idS){
		Mois leMois = null;
		Cursor curseur;
		curseur = accesBD.getReadableDatabase().rawQuery("select * from mois where numM="+numM+" and numA="+numA+" and idS="+idS+" ;",null);
		if (curseur.getCount() > 0) {
			curseur.moveToFirst();
			leMois = new Mois(numM, numA, idS ,curseur.getString(3));
		}
		return leMois;
	}
	
	public ArrayList<Mois> getMois(){
		Cursor curseur;
		String req = "select * from mois";
		curseur = accesBD.getReadableDatabase().rawQuery(req,null);
		return cursorToMoisArrayList(curseur);
	}
	
	
	private ArrayList<Mois> cursorToMoisArrayList(Cursor curseur){
		ArrayList<Mois> listeMois = new ArrayList<Mois>();
		long numM;
		long numA;
		long idS;
		String remarquesM;	
		
		curseur.moveToFirst();
		while (!curseur.isAfterLast()){
			numM = curseur.getLong(0);
			numA = curseur.getLong(1);
			idS = curseur.getLong(2);
			remarquesM = curseur.getString(3);
			listeMois.add(new Mois(numM, numA, idS, remarquesM));
			curseur.moveToNext();
		}
		
		return listeMois;
	}
	
	
}
