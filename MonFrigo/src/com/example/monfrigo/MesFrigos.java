package com.example.monfrigo;

import java.util.ArrayList;
import java.util.List;

public class MesFrigos{
	private static List<Frigo> mesFrigos = new ArrayList<Frigo>();
	private static Frigo frigoActuel = new Frigo("test");
	
	public MesFrigos(){
	}
	
	public static void ajouterFrigo(String nom){
		mesFrigos.add(new Frigo(nom));
		mesFrigos.add(frigoActuel);
		//pour tester
		mesFrigos.add(new Frigo("frigo2"));
		mesFrigos.add(new Frigo("frigo3"));
		mesFrigos.add(new Frigo("frigo4"));
		mesFrigos.add(new Frigo("frigo5"));
		mesFrigos.add(new Frigo("frigo6"));
	}
	
	public static void supprimerFrigo(String nom){
		mesFrigos.remove(nom);
	}
	
	public static Frigo getUnFrigo(String nom){
		for(Frigo f : mesFrigos){
			if(f.getNom().equals(nom))
				return f;
		}
		return null;
	}
	
	public static List<String> getMesFrigos(){
		List<String> ListeDesFrigos = new ArrayList<String>();
		
		for(Frigo f : mesFrigos){
			ListeDesFrigos.add(f.getNom());
		}
		return ListeDesFrigos;
	}
	
	public static Frigo getFrigoActuel(){
		return frigoActuel;
	}

	public static void setFrigoActuel(Frigo frigoActuel) {
		MesFrigos.frigoActuel = frigoActuel;
	}
	
}
