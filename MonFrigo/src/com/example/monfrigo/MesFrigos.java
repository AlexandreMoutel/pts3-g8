package com.example.monfrigo;

import java.util.List;

import android.util.Log;

public class MesFrigos extends Frigo {
	
	private static Frigo monFrigo;
	
	public void creerFrigo(){
		setMonFrigo(new Frigo());
	}

	public List<Aliment> getMonFrigo() {
		return  monFrigo.getLeFrigo();
	}

	public static void setMonFrigo(Frigo monFrigoRecu) {
		monFrigo = monFrigoRecu;
		Log.e("DEBUG", "Contenu de la liste : "+monFrigo);
	}
}
