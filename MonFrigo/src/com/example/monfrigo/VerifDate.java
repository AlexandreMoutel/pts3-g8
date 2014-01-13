package com.example.monfrigo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class VerifDate extends Service {

	/*
	 * Cette classe est un service qui s'execute toutes les 24 heures et gère la création des notification
	 */
	List listeFrigo;
	List<Aliment> listeAliment;
	int compteur;
	
	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

		final Runnable task = new Runnable() {

			@Override
			public void run() {
				compteur++;
				Calendar cal = Calendar.getInstance();
				/**
				 * On regarde si un frigo existe
				 */
				if(MesFrigos.getMesFrigos() != null){
					ArrayList<String> mf = (ArrayList<String>) MesFrigos.getMesFrigos();
					/**
					 * Pour chaque frigo
					 */
					for(String frigo : mf){
						MesFrigos.setFrigoActuel(frigo);
						/**
						 * On regarde si le frigo est vide
						 */
						if(MesFrigos.getFrigoActuel().getLeFrigo() != null){
							ArrayList<Aliment> al = (ArrayList<Aliment>) MesFrigos.getFrigoActuel().getLeFrigo();
							/**
							 * Pour chaque aliment du frigo
							 */
							for(Aliment a : al){
								StringTokenizer st = new StringTokenizer(a.getDate());
								String jour = st.nextToken("/");
								String mois = st.nextToken("/");
								String annee = st.nextToken();
								int intAnnee = Integer.parseInt(annee);
								int intMois = Integer.parseInt(mois);
								int intJour = Integer.parseInt(jour);
								
								if(intAnnee == cal.get(Calendar.YEAR)){
									if(intMois == (cal.get(Calendar.MONTH)+1)){
										if(intJour == cal.get(Calendar.DAY_OF_MONTH)){
											createNotify(a, 0);
										}
										else if(intJour == (cal.get(Calendar.DAY_OF_MONTH) +1)){
											createNotify(a, 1);
										}
										else if(intJour == (cal.get(Calendar.DAY_OF_MONTH) +2)){
											createNotify(a, 2);
										}
									}
								}
								
								if(compteur == 31){
									createNotify(a, -1); //Dégivrage du frigo
								}
							}
						}
					}
				}

			}    
		};

		executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.DAYS);




	}
	/**
	 * Fonction qui crée une notification
	 */
	public void createNotify(Aliment monAliment, int nbJours){
		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);        
		String texteNotification;
		int ID_NOTIFICATION;
		String titreNotification = "Péremption d'un Aliment !";
		
		/**
		 * On créer l'ID en fonction du nombre de jours avant la péremption de l'Aliment
		 */
		if(nbJours == 2){
			texteNotification = "Péremption d'un produit du nom de : " + monAliment.getNom() + " dans " + nbJours + " jours.";   
			ID_NOTIFICATION = MesFrigos.getFrigoActuel().heyLesAlimentsIlsOntUnNombreCestDroleNon(monAliment) + 20000;
		}
		else if(nbJours == 1){
			texteNotification = "Péremption d'un produit du nom de : " + monAliment.getNom() + " dans " + nbJours + " jour.";   
			ID_NOTIFICATION = MesFrigos.getFrigoActuel().heyLesAlimentsIlsOntUnNombreCestDroleNon(monAliment) + 10000;
		}
		else if(nbJours == -1){
			titreNotification = "Dégivrage du frigo !";
			texteNotification = "Il est temps de dégivrer votre frigo !";   
			ID_NOTIFICATION = MesFrigos.getFrigoActuel().heyLesAlimentsIlsOntUnNombreCestDroleNon(monAliment) + 30000;
			compteur = 0;
		}
		else{
			ID_NOTIFICATION = MesFrigos.getFrigoActuel().heyLesAlimentsIlsOntUnNombreCestDroleNon(monAliment);
			texteNotification = "Péremption d'un produit du nom de : " + monAliment.getNom() + " aujourd'hui";   
		}


		Notification notification = new Notification(R.drawable.icon, "Péremption d'un aliment bientôt !", System.currentTimeMillis());
		

		/**
		 * On envoie les infos de l'aliment a ActivityNotification pour pouvoir le récupérer
		 */
		Intent t = new Intent(this, ActivityNotification.class);
		t.putExtra("monAlimentNom", monAliment.getNom());
		t.putExtra("monAlimentType", monAliment.getType());
		t.putExtra("monAlimentQuantite", monAliment.getQuantite());
		t.putExtra("monAlimentDate", monAliment.getDate());
		t.putExtra("nbJours", nbJours);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, t, 0);

		notification.setLatestEventInfo(this, titreNotification, texteNotification, pendingIntent);

		/**
		 * On ajoute la notification avec un ID correspondant
		 */
		notificationManager.notify(ID_NOTIFICATION, notification);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}

}
