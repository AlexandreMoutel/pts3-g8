package com.example.monfrigo;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
 
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
 
	private static LaBDD laBelleDindeDorée;
	public TabHost tabHost;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        laBelleDindeDorée = new LaBDD(this, "Gestion de frigo", null, 1);
        
        this.tabHost = getTabHost();
        
        setupTab("Ajout", "tab1", new Intent().setClass(this, Ajout.class));
        setupTab("Mon Frigo", "tab2", new Intent().setClass(this, Liste.class));
        setupTab("Paramètre", "tab3", new Intent().setClass(this, CreerFrigo.class));
        setupTab("Liste De Course", "tab4", new Intent().setClass(this, AfficherListeDeCourse.class)); 
        
        this.tabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
        
    }
    
    public static LaBDD getLaBelleDindeDorée(){
    	return laBelleDindeDorée;
    }
    
    private void setupTab(String name, String tag, Intent intent) {
		tabHost.addTab(tabHost.newTabSpec(tag).setIndicator(createTabView(tabHost.getContext(), name)).setContent(intent));
	}
 
	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
 
		return view;
	}
}	





