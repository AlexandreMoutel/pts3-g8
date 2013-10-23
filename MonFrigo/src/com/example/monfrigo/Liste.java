package com.example.monfrigo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Liste extends Activity {
     
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_liste);
	     //ListView maListe = (ListView) findViewById(R.id.listView_liste);
	     
	     //List<Aliment> leFrigo = MesFrigos.getUnFrigo("Frigo1").getLeFrigo();
	          
	     //ArrayAdapter<Aliment> adapter = new ArrayAdapter<Aliment>(this, android.R.layout.simple_list_item_1, leFrigo);
	    
	    //----------------TEST-------------------------------------
	  //On r�cup�re une ListView de notre layout en XML, c'est la vue
	   // qui repr�sente la liste
	     ListView vue = (ListView) findViewById(R.id.listView_liste);
	     
	     /*
	    * On entrepose nos donn�es dans un tableau qui contient deux
	    colonnes :
	    * - la premi�re contiendra le nom de l'utilisateur
	    * - la seconde contiendra le num�ro de t�l�phone de l'utilisateur
	    */
	     String[][] repertoire = new String[][]{
	     {"Bill Gates", "06 06 06 06 06"},
	     {"Niels Bohr", "05 05 05 05 05"},
	     {"Alexandre III de Mac�doine", "04 04 04 04 04"}};
	     
	     /*
	    * On doit donner � notre adaptateur une liste du type �
	    List<Map<String, ?> � :
	    * - la cl� doit forc�ment �tre une cha�ne de caract�res
	    * - en revanche, la valeur peut �tre n'importe quoi, un objet ou un
	    entier par exemple,
	    * si c'est un objet, on affichera son contenu avec la m�thode �
	    toString() �
	    *
	    * Dans notre cas, la valeur sera une cha�ne de caract�res, puisque
	    le nom et le num�ro de t�l�phone
	    * sont entrepos�s dans des cha�nes de caract�res
	    */
	     List<HashMap<String, String>> liste = new
	    ArrayList<HashMap<String, String>>();
	     
	     HashMap<String, String> element;
	     //Pour chaque personne dans notre r�pertoire�
	     for(int i = 0 ; i < repertoire.length ; i++) {
	     //� on cr�e un �l�ment pour la liste�
	     element = new HashMap<String, String>();
	     /*
	    * � on d�clare que la cl� est � text1 � (j'ai choisi ce mot au
	    hasard, sans sens technique particulier) 
	    * pour le nom de la personne (premi�re dimension du tableau de
	    valeurs)�
	    */
	     element.put("text1", repertoire[i][0]);
	     /*
	    * � on d�clare que la cl� est � text2 �
	    * pour le num�ro de cette personne (seconde dimension du tableau de
	    valeurs)
	    */
	     element.put("text2", repertoire[i][1]);
	     liste.add(element);
	     }
	     
	     ListAdapter adapter = new SimpleAdapter(this, 
	     //Valeurs � ins�rer
	     liste, 
	     /*
	    * Layout de chaque �l�ment (l�, il s'agit d'un layout par d�faut
	    * pour avoir deux textes l'un au-dessus de l'autre, c'est pourquoi
	    on 
	    * n'affiche que le nom et le num�ro d'une personne)
	    */
	     android.R.layout.simple_list_item_2,
	     /*
	    * Les cl�s des informations � afficher pour chaque �l�ment :
	    * - la valeur associ�e � la cl� � text1 � sera la premi�re
	    information
	    * - la valeur associ�e � la cl� � text2 � sera la seconde
	    information
	    */
	     new String[] {"text1", "text2"}, 
	     /*
	     * Enfin, les layouts � appliquer � chaque widget de notre �l�ment
	     * (ce sont des layouts fournis par d�faut) :
	     * - la premi�re information appliquera le layout �
	     android.R.id.text1 �
	     * - la seconde information appliquera le layout �
	     android.R.id.text2 �
	     */
	      new int[] {android.R.id.text1, android.R.id.text2 });
	      //Pour finir, on donne � la ListView le SimpleAdapter
	      vue.setAdapter(adapter);
	     // maListe.setAdapter(adapter);
	      }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.liste, menu);
		return true;
	}

}
