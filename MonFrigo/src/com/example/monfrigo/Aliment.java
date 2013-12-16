package com.example.monfrigo;


public class Aliment {

	private String nom, type, date, quantite;
	
	public Aliment(String nom, String type, String date, String quantite) {
		super();
		this.nom = nom;
		this.type = type;
		this.date = date;
		this.quantite = quantite;
	}

	public String getNom() {
		return nom;
	}

	public String getType() {
		return type;
	}

	public String getDate() {
		return date;
	}

	public String getQuantite() {
		return quantite;
	}

	@Override
	public String toString() {
		return ""+nom+"   "+type+"   "+date+"   "+quantite;
	}
}
