package com.example.monfrigo;

import java.util.Date;

import android.widget.TextView;

public class Aliment {

	private TextView nom, type, date;
	private int quantite;
	
	public Aliment(TextView nom, TextView type, TextView date, int quantite) {
		super();
		this.nom = nom;
		this.type = type;
		this.date = date;
		this.quantite = quantite;
	}

	public TextView getNom() {
		return nom;
	}

	public TextView getType() {
		return type;
	}

	public TextView getDate() {
		return date;
	}

	public int getQuantite() {
		return quantite;
	}
	
	
}
