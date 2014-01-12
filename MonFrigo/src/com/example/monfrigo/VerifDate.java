package com.example.monfrigo;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class VerifDate extends Service {

	List listeFrigo;
	List<Aliment> listeAliment;

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
				Log.e("RUN", "Dans le run");
				listeFrigo = MesFrigos.getMesFrigos();
				if(listeFrigo != null)
					recupererAliment();

			}           
		};

		executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);




	}
	
	public void recupererAliment(){
		for(int i = 0; i < listeFrigo.size(); i++){ //On parcourt la liste de frigo
			Log.e("valeur de i : ", "" + i);
			String nom = listeFrigo.get(i).toString();
			Log.e("nom du frigo actuel : ", "" + nom);
			Frigo frigo= MesFrigos.getUnFrigo(nom);
			Log.e("Contenu frigo : ", ""+frigo.getNom() + " : " + frigo.getLeFrigo());
			listeAliment = frigo.getLeFrigo();
			Log.e("listeAliment", "" + listeAliment);
			
			for(int j = 0; j < MesFrigos.getUnFrigo(listeFrigo.get(i).toString()).getLeFrigo().size(); j++){ //On parcourt la liste d'aliment de chaque frigo
				listeAliment.add(MesFrigos.getUnFrigo(listeFrigo.get(i).toString()).getLeFrigo().get(j));
				Log.e("2", "SECOND FOR DE LA MORKITU");
			}
			
			
		}
		
		comparerDate();
	}

	private void comparerDate() {
		Log.e("passage", "comparerDate");
		this.onCreate();
		
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
