package com.example.monfrigo;

public class MesFrigos extends Frigo {
	
	public static Frigo monFrigo;
	
	public void creerFrigo(){
		monFrigo = new Frigo();
	}
	
	public Frigo getMesFrigos(){
		return monFrigo;
	}
}
