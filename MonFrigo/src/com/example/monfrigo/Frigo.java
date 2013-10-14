package com.example.monfrigo;

import java.util.ArrayList;
import java.util.List;

public class Frigo {
	private List<Aliment> leFrigo;
	
	public Frigo(List<Aliment> leFrigo){
		super();
		leFrigo = new ArrayList<Aliment>();
	}
	
	public void ajouterAliment(Aliment leJambon){
		leFrigo.add(leJambon);
	}
}
