package com.example.monfrigo;

import java.util.ArrayList;
import java.util.List;

public class Frigo {
	private List<Aliment> leFrigo;
	private String nom;
	
	public Frigo(String nom){
		super();
		leFrigo = new ArrayList<Aliment>();
		this.nom = nom;
	}
	
	public void ajouterAliment(Aliment leJambon){
		leFrigo.add(leJambon);
	}
	
	public void mangerUnAliment(Aliment lePaté){
		leFrigo.remove(lePaté);
	}

	public List<Aliment> getLeFrigo() {
		return leFrigo;
	}
	
	public String getNom(){
		return nom;
	}

	@Override
	public String toString() {
		return "Frigo [leFrigo=" + leFrigo + "]";
	}
}
