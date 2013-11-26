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
 
	public TabHost tabHost;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        this.tabHost = getTabHost();
        
        setupTab("Ajout", "tab1", new Intent().setClass(this, Ajout.class));
        setupTab("Liste", "tab2", new Intent().setClass(this, Liste.class));
        setupTab("Mes Frigos", "tab3", new Intent().setClass(this, CreerFrigo.class));
        setupTab("Paramètre", "tab4", new Intent().setClass(this, Ajout.class));
        setupTab("Liste De Course", "tab5", new Intent().setClass(this, AfficherListeDeCourse.class));
        
        this.tabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
        
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





