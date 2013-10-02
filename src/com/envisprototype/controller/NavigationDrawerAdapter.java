package com.envisprototype.controller;

import java.util.List;

import com.envisprototype.model.drawer.DrawerMenuItem;
import com.envisprototype.R;

import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavigationDrawerAdapter extends ArrayAdapter<DrawerMenuItem> {

	private List<DrawerMenuItem> items;

	public NavigationDrawerAdapter(Context context, int resource,
			List<DrawerMenuItem> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.items = objects;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			v = inflater.inflate(R.layout.drawer_example, null);
		}
		DrawerMenuItem item = items.get(position);
		if (item != null) {
			TextView menuTV = (TextView) v.findViewById(R.id.menuItemLbl);
			ImageView menuIcon = (ImageView) v.findViewById(R.id.menuIcon);
			if (items.get(position) != null) {
				menuTV.setText(items.get(position).getMenuName());
				menuIcon.setImageResource(items.get(position).getMenuIcon());
			}
		}
		return v;

	}

}
