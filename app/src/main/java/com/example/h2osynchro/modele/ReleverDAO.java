package com.example.h2osynchro.modele;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ReleverDAO {
	private static String base = "BDh2o";
	private static int version = 1;
	BD_SQLiteOpenHelper accesBD;
	
	public ReleverDAO(Context ct){
		accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);	
	}
	
	
	public long addRelever(Relever unRelever){
		long ret;
		SQLiteDatabase bd = accesBD.getWritableDatabase();
		
		ContentValues value = new ContentValues();
		value.put("numM", unRelever.getNumM());
		value.put("numA", unRelever.getNumA());
		value.put("idS", unRelever.getIdS());
		value.put("idC", unRelever.getIdC());
		value.put("numJ", unRelever.getNumJ());
		value.put("qteEntreeR", unRelever.getQteEntreeR());
		value.put("qteSortieR", unRelever.getQteSortieR());

		ret = bd.insert("relever", null, value);
		
		return ret;
	}
	
		
	public Relever getRelever(long numM, long numA, long idS, long idC, long numJ){
		Relever leReleve = null;
		Cursor curseur;
		curseur = accesBD.getReadableDatabase().rawQuery("select * from relever where numM="+numM+" and numA="+numA+" and idS="+idS+" and idC="+idC+" and numJ="+numJ+" ;",null);
		if (curseur.getCount() > 0) {
			curseur.moveToFirst();
			leReleve = new Relever(numM, numA, idS, idC, numJ, curseur.getInt(5), curseur.getInt(6));
		}
		return leReleve;
	}
	
	public ArrayList<Relever> getRelever(){
		Cursor curseur;
		String req = "select * from relever";
		curseur = accesBD.getReadableDatabase().rawQuery(req,null);
		return cursorToReleveArrayList(curseur);
	}
	
	
	private ArrayList<Relever> cursorToReleveArrayList(Cursor curseur){
		ArrayList<Relever> listeRelever = new ArrayList<Relever>();
		long numM;
		long numA;
		long idS;
		long idC;
		long numJ;
		int qteEntreeR;
		int qteSortieR;
		
		curseur.moveToFirst();
		while (!curseur.isAfterLast()){
			numM = curseur.getLong(0);
			numA = curseur.getLong(1);
			idS = curseur.getLong(2);
			idC = curseur.getLong(3);
			numJ = curseur.getLong(4);
			qteEntreeR = curseur.getInt(5);
			qteSortieR = curseur.getInt(6);
			
			listeRelever.add(new Relever(numM, numA, idS, idC, numJ, qteEntreeR, qteSortieR));
			curseur.moveToNext();
		}
		
		return listeRelever;
	}
	
	
}
