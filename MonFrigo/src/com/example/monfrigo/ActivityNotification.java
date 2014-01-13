package com.example.monfrigo;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.audiofx.NoiseSuppressor;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityNotification extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int ID_NOTIFICATION;
		Bundle b = getIntent().getExtras();
		
		 /**
		   * On recrée l'ID et le texte de la notification en fonction de nbJours
		   */
		  if(b.getInt("nbJours") == 2){
		   ID_NOTIFICATION = MesFrigos.getFrigoActuel().heyLesAlimentsIlsOntUnNombreCestDroleNon(
		     new Aliment(b.getString("monAlimentNom"), b.getString("monAlimentType"), 
		       b.getString("monAlimentDate"), b.getString("monALimentQuantite"))) + 20000;
		  }
		  else if(b.getInt("nbJours") == 1){
		   ID_NOTIFICATION = MesFrigos.getFrigoActuel().heyLesAlimentsIlsOntUnNombreCestDroleNon(
		     new Aliment(b.getString("monAlimentNom"), b.getString("monAlimentType"), 
		       b.getString("monAlimentDate"), b.getString("monALimentQuantite"))) + 10000;
		  }
		  else if(b.getInt("nbJours") == -1){
			   ID_NOTIFICATION = MesFrigos.getFrigoActuel().heyLesAlimentsIlsOntUnNombreCestDroleNon(
			     new Aliment(b.getString("monAlimentNom"), b.getString("monAlimentType"), 
			       b.getString("monAlimentDate"), b.getString("monALimentQuantite"))) + 30000;
			  }
		  else{
		   ID_NOTIFICATION = MesFrigos.getFrigoActuel().heyLesAlimentsIlsOntUnNombreCestDroleNon(
		     new Aliment(b.getString("monAlimentNom"), b.getString("monAlimentType"), 
		       b.getString("monAlimentDate"), b.getString("monALimentQuantite")));
		  }
		  
		//Lorsque l'utilisateur clique sur la notification, l'application s'ouvre
		setContentView(R.layout.activity_liste);
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		
		//On supprime ensuite la notification de la liste de notification
		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(ID_NOTIFICATION);
	}
}
