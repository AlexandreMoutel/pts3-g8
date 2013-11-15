package com.example.monfrigo;

import java.util.ArrayList;
import java.util.List;

public class ListeDeCourse {
	private final static List<Aliment> laListeDeBouffe = new ArrayList<Aliment>();
	
	public ListeDeCourse(){
		 
	}
	
	public void ajouterAliment(Aliment leGrosJambon){
		laListeDeBouffe.add(leGrosJambon);
	}
	
	public void acheterUnAliment(Aliment leBonFoieGras){
		laListeDeBouffe.remove(leBonFoieGras);
	}

	public static List<Aliment> getLaBouffeQuiFautAcheter() {
		return laListeDeBouffe;
	}
}
