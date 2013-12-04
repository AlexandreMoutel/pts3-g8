package com.example.monfrigo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MesFrigos{
	private final static String FRIGO = "Frigo";
	private final static String IDNOM = "Nom";
	private static SQLiteDatabase laBelleDindeDor�e;
	private static Frigo frigoActuel = new Frigo("Mon Premier Frigo");

	public MesFrigos(){
	}

	public static void open(){
		laBelleDindeDor�e = MainActivity.getLaBelleDindeDor�e().getWritableDatabase();
	}

	public static void close(){
		laBelleDindeDor�e.close();
	}

	public static void ajouterFrigo(String nom){
		open();
		ContentValues values = new ContentValues();
		values.put(IDNOM, nom);
		laBelleDindeDor�e.insert(FRIGO, null, values);
		close();
	}

	public static void supprimerFrigo(String nom){
		open();
		laBelleDindeDor�e.delete(FRIGO, IDNOM + " = " + nom, null);
		close();
	}

	public static List<String> getMesFrigos(){
		open();
		List<String> ListeDesFrigos = new ArrayList<String>();
		Cursor laGrosseDinde = laBelleDindeDor�e.query(FRIGO, new String[] {IDNOM}, null, null, null, null, null);

		if(laGrosseDinde.getCount() == 0){
			return null;
		}
		else{
			if(laGrosseDinde.moveToFirst()){
				do{
					ListeDesFrigos.add(laGrosseDinde.getString(0));
				}while(laGrosseDinde.moveToNext());
			}
		}
		close();
		return ListeDesFrigos;
	}

	public static Frigo getUnFrigo(String nom){
		open();
		Frigo leFrigo = null;
		Cursor laGrosseDinde = laBelleDindeDor�e.query(FRIGO, new String[] {IDNOM}, IDNOM + " = " + nom, null, null, null, null);
		if(laGrosseDinde.getString(0).equals(nom)){
			if(laGrosseDinde.getCount() == 0){
				return null;
			}
			else{
				if(laGrosseDinde.moveToFirst()){
					leFrigo = new Frigo(nom);
				}
			}
			close();
			return leFrigo;
		}
		else 
			return null;
	}

	public static Frigo getFrigoActuel(){
		return frigoActuel;
	}

	public static void setFrigoActuel(String nom) {
		open();
		int count = 0;
		Cursor laGrosseDinde = laBelleDindeDor�e.query(FRIGO, new String[] {IDNOM}, null, null, null, null, null);

		if(laGrosseDinde.getCount() == 0){
			ajouterFrigo(nom);
		} 
		else{
			if(laGrosseDinde.moveToFirst()){
				do{
					if(laGrosseDinde.getString(0).equals(nom)){
						frigoActuel.setNom(laGrosseDinde.getString(0));
					}
				}while(laGrosseDinde.moveToNext());
			}
		}
		close();
	}
}
