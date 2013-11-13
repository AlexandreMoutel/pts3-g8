package com.example.monfrigo;

import java.util.ArrayList;
import java.util.List;
public class MesFrigos{
	private static List<Frigo> mesFrigos = new ArrayList<Frigo>();
	
	public MesFrigos(){
	}
	
	public static void ajouterFrigo(String nom){
		mesFrigos.add(new Frigo(nom));
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
	
}
