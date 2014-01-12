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
		/*
		 * On récupèr les infos de l'aliment concerné
		 */
		Bundle b = getIntent().getExtras();

		//On crée un TextView
		TextView txt = new TextView(this);

		/**
		 * On recrée l'ID et le texte de la notification en fonction de nbJours
		 */
		/*
		if(b.getInt("nbJours") == 2){
			ID_NOTIFICATION = MesFrigos.getFrigoActuel().heyLesAlimentsIlsOntUnNombreCestDroleNon(
					new Aliment(b.getString("monAlimentNom"), b.getString("monAlimentType"), 
							b.getString("monAlimentDate"), b.getString("monALimentQuantite"))) + 20000;
			txt.setText("Un aliment de votre frigo arrive bientôt à sa date de péremption ! Il vous reste 2 jours pour le manger. " +
					"\nJe vous invite à consulter rapidement votre frigo");
		}
		else if(b.getInt("nbJours") == 1){
			ID_NOTIFICATION = MesFrigos.getFrigoActuel().heyLesAlimentsIlsOntUnNombreCestDroleNon(
					new Aliment(b.getString("monAlimentNom"), b.getString("monAlimentType"), 
							b.getString("monAlimentDate"), b.getString("monALimentQuantite"))) + 10000;
			txt.setText("Un aliment de votre frigo arrive bientôt à sa date de péremption ! Il vous reste 1 jour pour le manger. " +
					"\nJe vous invite à consulter rapidement votre frigo");
		}
		else{
			ID_NOTIFICATION = MesFrigos.getFrigoActuel().heyLesAlimentsIlsOntUnNombreCestDroleNon(
					new Aliment(b.getString("monAlimentNom"), b.getString("monAlimentType"), 
							b.getString("monAlimentDate"), b.getString("monALimentQuantite")));
			txt.setText("Un aliment de votre frigo arrive aujourd'hui à sa date de péremption ! Consulter votre frigo pour plus d'informations");
		}*/
		setContentView(R.layout.activity_main);
		new Intent().setClass(this, Liste.class);

		//On ajoute notre TextView à la vue
		//setContentView(txt);

		//On supprime la notification de la liste de notification
		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		//notificationManager.cancel(ID_NOTIFICATION);
	}
}
