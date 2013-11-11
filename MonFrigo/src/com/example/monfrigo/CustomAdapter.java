package com.example.monfrigo;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<HashMap<String, Object>>
{
	
	ArrayList<HashMap<String, Object>> aliment = new ArrayList<HashMap<String,Object>>();
	LayoutInflater inflater;

	public CustomAdapter(Context context, int textViewResourceId,
			ArrayList<HashMap<String, Object>> aliment, LayoutInflater inflater) {
		super(context, textViewResourceId, aliment);
		this.aliment = aliment;
		this.inflater = inflater;
	}


	private class ViewHolder
	{
		TextView nom, type, quantite, date;

	}

	ViewHolder viewHolder;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView==null)
		{                             
			convertView = inflater.inflate(R.layout.liste_aliment, null);
			viewHolder=new ViewHolder();

			viewHolder.nom=(TextView) convertView.findViewById(R.id.nomAliment);
			viewHolder.type=(TextView) convertView.findViewById(R.id.typeAliment);
			viewHolder.quantite=(TextView) convertView.findViewById(R.id.quantiteAliment);
			viewHolder.date=(TextView) convertView.findViewById(R.id.dateAliment);

			convertView.setTag(viewHolder);

		}
		else
			viewHolder=(ViewHolder) convertView.getTag();

		//On remplace les textView
		viewHolder.nom.setText(aliment.get(position).get("nom").toString());
		viewHolder.type.setText(aliment.get(position).get("type").toString());
		viewHolder.quantite.setText(aliment.get(position).get("quantite").toString());
		viewHolder.date.setText(aliment.get(position).get("date").toString());

		return convertView;
	}

}