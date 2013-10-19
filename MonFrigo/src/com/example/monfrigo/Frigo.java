package com.example.monfrigo;

import java.util.ArrayList;
import java.util.List;

public class Frigo {
	private List<Aliment> leFrigo;
	
	public Frigo(){
		super();
		leFrigo = new ArrayList<Aliment>();
	}
	
	public void ajouterAliment(Aliment leJambon){
		leFrigo.add(leJambon);
	}

	public List<Aliment> getLeFrigo() {
		return leFrigo;
	}

	@Override
	public String toString() {
		return "Frigo [leFrigo=" + leFrigo.get(0) + "]";
	}
}
