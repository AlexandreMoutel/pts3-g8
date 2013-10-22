package com.example.monfrigo;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class MesFrigos{
	private static List<Frigo> mesFrigos;
	
	public MesFrigos(){
		mesFrigos = new ArrayList<Frigo>();
	}
	
	public void ajouterFrigo(String nom){
		mesFrigos.add(new Frigo(nom));
	}
	
	public static Frigo getUnFrigo(String nom){
		for(Frigo f : mesFrigos){
			if(f.getNom().equals(nom))
				return f;
		}
		return null;
	}
	
}
