package com.example.h2osynchro.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BD_SQLiteOpenHelper extends SQLiteOpenHelper {
	
	private String creaTableStation="create table station ( "
			+ " 	idS integer, "
			+ " 	nomS text, "
			+ " 	primary key (idS) "
			+ " ); ";

			private String creaTableCritere = " create table critere ( "
			+ " 	idC integer, "
			+ " 	libelleC text, "
			+ " 	uniteC text, "
			+ " 	primary key (idC) "
			+ " ); ";

			private String creaTableAnnee = " create table annee ( "
			+ " 	numA integer, "
			+ " 	idS integer, "
			+ " 	introA text, "
			+ " 	conclusionA text, "
			+ " 	primary key (numA,idS), "
			+ " 	foreign key(idS) references station(idS) "
			+ " ); ";

			private String creaTableMois = " create table mois ( "
			+ " 	numM integer, "
			+ " 	numA integer, "
			+ " 	idS integer, "
			+ " 	remarquesM text, "
			+ " 	primary key (numM,numA,idS), "
			+ " 	foreign key(numA,idS) references annee(numA,idS) "
			+ " )";

			private String creaTableRelever = " create table relever ( "
			+ " 	numM integer, "
			+ " 	numA integer, "
			+ " 	idS integer, "
			+ " 	idC integer, "
			+ " 	numJ integer, "
			+ " 	qteEntreeR integer, "
			+ " 	qteSortieR integer, "
			+ " 	primary key (numM,numA,idS,idC,numJ), "
			+ " 	foreign key(numM,numA,idS) references mois(numM,numA,idS), "
			+ " 	foreign key(idC) references critere(idC) "
			+ " ); ";


	public BD_SQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(creaTableStation);
		db.execSQL(creaTableCritere);
		db.execSQL(creaTableAnnee);
		db.execSQL(creaTableMois);
		db.execSQL(creaTableRelever);

		db.execSQL("insert into station (idS,nomS) values(1,'Landerrouat');");
		db.execSQL("insert into critere (idC,libelleC, uniteC) values(1,'Eau','m3');");
		db.execSQL("insert into critere (idC,libelleC, uniteC) values(2,'DCO','mg/l');");
		db.execSQL("insert into critere (idC,libelleC, uniteC) values(3,'MES','mg/l');");
		Log.d("log","base de test cree");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
