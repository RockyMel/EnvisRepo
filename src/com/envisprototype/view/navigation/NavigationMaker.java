package com.envisprototype.view.navigation;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SpinnerAdapter;

import com.envisprototype.R;
import com.envisprototype.controller.navigation.onDrawerItemClickListener;
import com.envisprototype.controller.navigation.onDropDownItemSelectedListener;
import com.envisprototype.model.drawer.DrawerMenuItem;
import com.envisprototype.view.model.NavigationDrawerAdapter;

public class NavigationMaker implements OnNavigationListener{
	public static NavigationDrawerAdapter listAdapter;
	public static ListView listView;
	public static List<DrawerMenuItem> listDataHeader;
	public static ActionBarDrawerToggle drawerToggle;
	public static String[] strings;

	private static void prepareListData() {
		listDataHeader = new ArrayList<DrawerMenuItem>();
		listDataHeader.add(new DrawerMenuItem("Home", R.drawable.favourites));
		listDataHeader.add(new DrawerMenuItem("Admin", R.drawable.admin));
		listDataHeader.add(new DrawerMenuItem("2D charts", R.drawable.two_d_chart));
		listDataHeader.add(new DrawerMenuItem("3D Visual", R.drawable.three_d_chart));
		listDataHeader.add(new DrawerMenuItem("Scan & View", R.drawable.ic_qrlauncher));
		listDataHeader.add(new DrawerMenuItem("Smart Map", R.drawable.add_map));
	}

	public static void makeNavigationDrawer(Context context) {
		((Activity) context).getActionBar().setDisplayHomeAsUpEnabled(true);
		((Activity) context).getActionBar().setHomeButtonEnabled(true);
		((Activity) context).getActionBar().setDisplayShowHomeEnabled(false);
		//((Activity) context).getActionBar().setDisplayShowTitleEnabled(false);
		DrawerLayout drawer = (DrawerLayout) ((Activity) context)
				.findViewById(R.id.drawer_layout);
		listView = (ListView) ((Activity) context).findViewById(R.id.leftEx);
		//listView.setBackgroundColor(color.holo_blue_light);
		prepareListData();
		drawerToggle = new ActionBarDrawerToggle((Activity) context, drawer,
				R.drawable.ic_launcher, R.string.hello_world,
				R.string.hello_world) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				System.out.println("drawer closed");
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {

			}
		};

		drawer.setDrawerListener(drawerToggle);
		listAdapter = new NavigationDrawerAdapter(((Activity) context),
				R.layout.drawer_example, listDataHeader);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new onDrawerItemClickListener(drawer));
	}
	
	public static void makeDropDownMenu(Context context, int dropDownID){
		Activity activity = (Activity)context;
		ActionBar actionBar = activity.getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setTitle("");
		SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(context,
				dropDownID,
				R.layout.style_dropdown);
		strings = activity.getResources().getStringArray(
				dropDownID);
		actionBar.setListNavigationCallbacks(mSpinnerAdapter,
				new onDropDownItemSelectedListener(activity, dropDownID));		
	}

	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		// TODO Auto-generated method stub
		Log.i("ActionBar", "asdf");
		return true;
	}

}
