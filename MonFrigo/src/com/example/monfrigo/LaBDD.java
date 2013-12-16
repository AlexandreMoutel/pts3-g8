package com.example.monfrigo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


 
public class LaBDD extends SQLiteOpenHelper{
	//Nom + Colonne Table Aliment
	private final static String ALIMENT = "Aliment";
	private final static String NOM = "Nom";
	private final static String TYPE = "Type";
	private final static String DATEPEREMPTION = "DateDePeremption";
	private final static String QUANTITE = "Quantite";
	private final static String FRIGOETRANGER = "Frigo";
	
	//Nom + Colonne Table Aliment
	private final static String FRIGO = "Frigo";
	private final static String IDNOM = "Nom";
	
	//Requête Table Frigo
	private final static String CREATE_TABLE_FRIGO = "Create table " + FRIGO + " (" + IDNOM + " Text PRIMARY KEY not null);";
	
	//Requête Table Aliment
	private final static String CREATE_TABLE_ALIMENT = "Create table " + ALIMENT + " (" + NOM + " Text PRIMARY KEY not null, "
			+ TYPE + " Text not null, " + DATEPEREMPTION + " Text not null, " + QUANTITE + " Integer not null, " + FRIGOETRANGER + " Text);";
	
	//Requête Création BDD
	//private final static String laBDD = CREATE_TABLE_FRIGO + CREATE_TABLE_ALIMENT;
	
	public LaBDD(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_ALIMENT);
		db.execSQL(CREATE_TABLE_FRIGO);
		db.execSQL("Insert into Frigo values ('Mon_Premier_Frigo')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
}
