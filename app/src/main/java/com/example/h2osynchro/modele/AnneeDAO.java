package com.example.h2osynchro.modele;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AnneeDAO {
	private static String base = "BDh2o";
	private static int version = 1;
	BD_SQLiteOpenHelper accesBD;
	
	public AnneeDAO(Context ct){
		accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);	
	}
	
	
	public long addAnnee(Annee uneAnnee){
		long ret;
		SQLiteDatabase bd = accesBD.getWritableDatabase();
		
		ContentValues value = new ContentValues();
		value.put("numA", uneAnnee.getNumA());
		value.put("idS", uneAnnee.getIdS());
		value.put("introA", uneAnnee.getIntroA());
		value.put("conclusionA",uneAnnee.getConclusionA());
		
		ret = bd.insert("annee", null, value);
		return ret;
	}
	
		
	public Annee getAnnee(long numA, long idS){
		Annee uneAnnee = null;
		Cursor curseur;
		curseur = accesBD.getReadableDatabase().rawQuery("select * from annee where numA="+numA+" and idS="+idS+";",null);
		if (curseur.getCount() > 0) {
			curseur.moveToFirst();
			uneAnnee = new Annee(numA, idS, curseur.getString(2), curseur.getString(3));
		}
		return uneAnnee;
	}
	
	public ArrayList<Annee> getAnnees(){
		Cursor curseur;
		String req = "select * from annee";
		curseur = accesBD.getReadableDatabase().rawQuery(req,null);
		return cursorToAnneeArrayList(curseur);
	}
	
	
	private ArrayList<Annee> cursorToAnneeArrayList(Cursor curseur){
		ArrayList<Annee> listeAnnee = new ArrayList<Annee>();
		long numA;
		long idS;
		String introA;
		String conclusionA;
		
		curseur.moveToFirst();
		while (!curseur.isAfterLast()){
			numA = curseur.getLong(0);
			idS = curseur.getLong(1);
			introA = curseur.getString(2);
			conclusionA = curseur.getString(3);
			listeAnnee.add(new Annee(numA, idS, introA, conclusionA));
			curseur.moveToNext();
		}
		return listeAnnee;
	}
	
}
