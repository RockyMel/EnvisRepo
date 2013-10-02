// Modified from http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
package com.envisprototype.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.envisprototype.R;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	protected Context context;
	private List<String> listDataHeader;
	private HashMap<String, List<String>> listDataChild;

	public ExpandableListAdapter(Context context, List<String> listDataHeader,
			HashMap<String, List<String>> listDataChild) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.listDataHeader = listDataHeader; // storing names for sensor types
		this.listDataChild = listDataChild; // storing names for particular
											// sensors
		checkIfChildNull();
	}

	private void checkIfChildNull() {
		String header;
		List<String> child;
		for (int i = 0; i < listDataHeader.size(); i++) {
			header = listDataHeader.get(i);
			for (int j = 0; j < listDataChild.size(); j++) {
				child = listDataChild.get(header);
				if (child == null) {
					listDataChild.put(header, new ArrayList<String>());
				}
			}
		}
	}

	@Override
	public Object getChild(int groupPos, int childPos) {
		// TODO Auto-generated method stub
		return this.listDataChild.get(this.listDataHeader.get(groupPos)).get(
				childPos);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPos, int childPos, boolean isLastChild,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// Getting one sensor with a layout
		final String childText = (String) getChild(groupPos, childPos);
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.sensors_child_list, null);
		}

		CheckBox childBox = (CheckBox) convertView.findViewById(R.id.sensorBox);
		if (childText != null){
			childBox.setText(childText);
			childBox.setOnCheckedChangeListener(new OneSensorChosenListener());
		}
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPos) {
		// TODO Auto-generated method stub
		return this.listDataChild.get(this.listDataHeader.get(groupPos)).size();
	}

	@Override
	public Object getGroup(int groupPos) {
		// TODO Auto-generated method stub
		return this.listDataHeader.get(groupPos);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return this.listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPos) {
		// TODO Auto-generated method stub
		return groupPos;
	}

	@Override
	public View getGroupView(int groupPos, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// Getting one type of sensors with a layout
		String headerTitle = (String) getGroup(groupPos);
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_group, null);
		}

		TextView textListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		textListHeader.setTypeface(null, Typeface.BOLD);
		textListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPos, int childPos) {
		// TODO Auto-generated method stub
		return true;
	}

}
