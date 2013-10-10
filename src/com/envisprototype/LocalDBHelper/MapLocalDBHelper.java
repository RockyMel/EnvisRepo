package com.envisprototype.LocalDBHelper;


import java.util.ArrayList;

import java.util.List;
import java.util.StringTokenizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;


import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapListInterface;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.maps.MapModel;


public class MapLocalDBHelper extends SQLiteOpenHelper implements MapListInterface  {

	private static MapLocalDBHelper singletonInstance;
	//class to store events "list" works better with array adapter(which needs a list)
	private MapListInterface mapModel=MapListModel.getSingletonInstance();
	
	private static final String DBNAME="EnvisDB.db";
	private static final int VERSION=1;
	
	private static final String TABLE_NAME="Maps";
	private static final String IDCOL="MapID";
	private static final String NAMECOL="MapName";
	private static final String LONGCOL="Longitude";
	private static final String LATCOL="Latitude";
	private static final String XCOORCOL="X";
	private static final String YCOORCOL="Y";
	private static final String ZCOORCOL="Z";
	private static final String NOTESCOL="Notes";
	
	
	
	
	public MapLocalDBHelper(Context context) {
		// TODO Auto-generated constructor stub

		super(context.getApplicationContext(),DBNAME,null,VERSION);
		
	}

	@Override
	public void editMap(MapInterface map) {
		// TODO Auto-generated method stub
		
		ContentValues values = prepareValues(map);
		getWritableDatabase().update(TABLE_NAME, values, IDCOL + "= \""
				+ map.getId() + "\"",null);
	
			mapModel.addMap(map);
	}
	
	@Override
	public void addMap(MapInterface map) {
		// TODO Auto-generated method stub
		ContentValues values = prepareValues(map);
		
		getWritableDatabase().insert(TABLE_NAME, null, values);
	
			mapModel.addMap(map);
		
	}


	@Override
	public void removeMap(MapInterface map) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public MapInterface findMapById(String Id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<MapInterface> getMapList() {
		// TODO Auto-generated method stub

		List<MapInterface> temp=new ArrayList<MapInterface>();
		
		
		String query="SELECT * FROM " + TABLE_NAME + ";";
		Log.i("QUERY getMapList", query);
		
		Cursor cursor = getWritableDatabase().rawQuery(query, null);
		Location location =  new Location(LocationManager.NETWORK_PROVIDER);
		if(cursor.moveToFirst())
		{
			do{
				//Log.i("asjdhkas", cursor.getString(1));
				MapInterface tempmap=new MapModel();
				tempmap.setId(cursor.getString(0));
				tempmap.setName(cursor.getString(1));
				//LOCATION....................................................!!!
				location.setLongitude(cursor.getDouble(2));
				location.setLatitude(cursor.getDouble(3));
				
				tempmap.setLocation(location);
				StringTokenizer Xcoor = new StringTokenizer(cursor.getString(4),",");
				int i = 0;
				Log.i("db helper coor", "the whole line is " + cursor.getString(4));
				Float abc= null;

				while(Xcoor.hasMoreElements()){
					i++;
					abc = Float.parseFloat((String) Xcoor.nextElement());
					tempmap.getRealCoordinates().getCoorX().add(abc);
					//Log.i("db helper coor", Integer.toString(i) + " ##### " + abc );
					
				}
				//Log.i("db helper coor", tempmap.getRealCoordinates().getXCoorString());
				StringTokenizer Ycoor = new StringTokenizer(cursor.getString(5),",");
				while(Ycoor.hasMoreElements()){
					tempmap.getRealCoordinates().getCoorY().add(Float.parseFloat((String) Ycoor.nextElement()));
					
					
				}
				
				tempmap.setzCoordinate(cursor.getFloat(6));
				
				//ADD NOTES TO MAP AT THIS POINT...............................!!!
	
				temp.add(tempmap);
			}while(cursor.moveToNext());
		}	
		
		return temp;
	
	
	}


//	@Override
//	public void resetModel(Context context) {
//		// TODO Auto-generated method stub
//		
//	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String queryString = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY,%s TEXT,%s DOUBLE,%s DOUBLE,%s TEXT,%s TEXT,%s FLOAT,%s TEXT);", TABLE_NAME,IDCOL,
				NAMECOL,LONGCOL,LATCOL,XCOORCOL,YCOORCOL,ZCOORCOL,NOTESCOL);
		db.execSQL(queryString);
		
	}


	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	public static MapListInterface getSingletonInstance(Context context) {
		if(singletonInstance==null)
			singletonInstance=new MapLocalDBHelper(context);

		return singletonInstance;
	}

	
	public void ReplicateMapList() {
		// TODO Auto-generated method stub

		List<MapInterface> themap = getMapList();
		MapListModel.getSingletonInstance().setMapList(themap);
		
	}



	@Override
	public void setMapList(List<MapInterface> mapList) {
		// TODO Auto-generated method stub
		
	}
	
	private ContentValues prepareValues(MapInterface map) {
		ContentValues values=new ContentValues();
		values.put(IDCOL, map.getId());
		values.put(NAMECOL, map.getName());
		//Log.i("checkDB", newevent.getName());
		values.put(LATCOL, map.getLocation().getLatitude());
		values.put(LONGCOL,map.getLocation().getLongitude());
		
		values.put(XCOORCOL, map.getRealCoordinates().getXCoorString());
		values.put(YCOORCOL,  map.getRealCoordinates().getYCoorString());
		values.put(ZCOORCOL,  map.getzCoordinate());
		
		values.put(NOTESCOL, "samplenote");
		return values;
	}


	



}
