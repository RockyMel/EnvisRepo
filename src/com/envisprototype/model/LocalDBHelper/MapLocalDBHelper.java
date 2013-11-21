package com.envisprototype.model.LocalDBHelper;


import java.util.ArrayList;

import java.util.List;
import java.util.StringTokenizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;


import com.envisprototype.model.DBHelper.MapInfoDBHelper;
import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapListInterface;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.maps.MapModel;
import com.envisprototype.model.processing.Coordinates;


public class MapLocalDBHelper extends SQLiteOpenHelper implements MapListInterface  {

	private static MapLocalDBHelper singletonInstance;
	//class to store events "list" works better with array adapter(which needs a list)
	private MapListInterface mapModel=MapListModel.getSingletonInstance();
	
	private Context context;
	private static final String TABLE_NAME="Maps";
	private static final String IDCOL="MapID";
	private static final String NAMECOL="MapName";
	private static final String LONGCOL="Longitude";
	private static final String LATCOL="Latitude";
	private static final String XCOORCOL="X";
	private static final String YCOORCOL="Y";
	private static final String ZCOORCOL="Z";
	private static final String NOTESCOL="Notes";
	private static final String CREATE_MAP_TABLE_QUERY = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY,%s TEXT,%s DOUBLE,%s DOUBLE,%s TEXT,%s TEXT,%s FLOAT,%s TEXT);", TABLE_NAME,IDCOL,
			NAMECOL,LONGCOL,LATCOL,XCOORCOL,YCOORCOL,ZCOORCOL,NOTESCOL);
	
	
	
	private MapLocalDBHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context.getApplicationContext(),EnvisDBAdapter.getDbname(),null,EnvisDBAdapter.getVersion());
		this.context = context;
	}

	@Override
	public void editMap(MapInterface map) {
		// TODO Auto-generated method stub
		
		ContentValues values = prepareValues(map);
		getWritableDatabase().update(TABLE_NAME, values, IDCOL + "= '"
				+ map.getId() + "'",null);
	
			mapModel.editMap(map);
	}
	@Override
	public void saveCoorsForMap(String mapId, Coordinates coorsToSave, float zCoor){
		ContentValues values = new ContentValues();
		String asdf = coorsToSave.getXCoorString();
		values.put(XCOORCOL, coorsToSave.getXCoorString());
		values.put(YCOORCOL, coorsToSave.getYCoorString());
		values.put(ZCOORCOL,  zCoor);
		int result = getWritableDatabase().update(TABLE_NAME, values, IDCOL + "= '"
				+ mapId + "'",null);
		MapListModel.getSingletonInstance().saveCoorsForMap(mapId, coorsToSave, zCoor);
		final String tempMapId = mapId;
		Thread thread = new Thread()
		{
			@Override
			public void run() {
				MapInfoDBHelper.editMap(MapListModel.getSingletonInstance().findMapById(tempMapId));
			}
		};
		thread.start();
	}
	
	@Override
	public void addMap(MapInterface map) {
		// TODO Auto-generated method stub
		ContentValues values = prepareValues(map);
		try{
		getWritableDatabase().insert(TABLE_NAME, null, values);
		}catch(Exception sqle){
			editMap(map);
			Log.i("sql","already exists");
		}
	
			mapModel.addMap(map);
		
	}
	
	


	@Override
	public void removeMap(MapInterface map) {
		// TODO Auto-generated method stub
		getWritableDatabase().delete(TABLE_NAME, IDCOL + "= \""
				+ map.getId() + "\"",null);
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
		Log.i("mapdb","in map oncreate");
		//db.execSQL(queryString);
		EnvisDBAdapter.getSingletonInstance(context).onCreate(db);
		
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
	
	
	
	public static String getCreateMapTableQuery() {
		return CREATE_MAP_TABLE_QUERY;
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
		
		values.put(NOTESCOL, map.getNotes());
		return values;
	}


	


}
