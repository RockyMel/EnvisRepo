package com.envisprototype.model.LocalDBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.envisprototype.model.processing.Coords;
import com.envisprototype.model.set.SetListModel;
import com.envisprototype.view.processing.SensorSet;


public class MapSetAssociationDBHelper extends SQLiteOpenHelper{
	private static MapSetAssociationDBHelper singletonInstance;
	//class to store events "list" works better with array adapter(which needs a list)
	//private SensorListInterface sensorModel=SensorListModel.getSingletonInstance();


	private static final String TABLE_NAME="MapSetAssociation";
	private static final String SETIDCOL="SetID";
	private static final String MAPIDCOL="MapID";
	private static final String XCOL="X";
	private static final String YCOL="Y";
	private static final String ZCOL="Z";
	private static final String CREATE_MAP_SET_ASS_TABLE_QUERY = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY,%s TEXT,%s FLOAT,%s FLOAT,%s FLOAT);", TABLE_NAME,SETIDCOL,
			MAPIDCOL,XCOL,YCOL,ZCOL);


	private Context context;

	private MapSetAssociationDBHelper(Context context) {
		// TODO Auto-generated constructor stub

		super(context.getApplicationContext(),EnvisDBAdapter.getDbname(),null,EnvisDBAdapter.getVersion());
		this.context = context;
	}

	public static MapSetAssociationDBHelper getSingletoneInstance(Context context){
		if(singletonInstance == null)
			singletonInstance = new MapSetAssociationDBHelper(context);
		return singletonInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		EnvisDBAdapter.getSingletonInstance(context).onCreate(db);
		//db.execSQL(CREATE_MAP_SET_ASS_TABLE_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}


	public void associateEnvisSensorsSetsWithMap( HashMap<String,SensorSet> envisSensors, String mapId){
		Iterator iterator = envisSensors.keySet().iterator();
		ContentValues values=new ContentValues();
		while(iterator.hasNext()){
			SensorSet tempSet = envisSensors.get(iterator.next());
			if(!tempSet.isIfSensor()){
				associateSetWithMap(tempSet.getId(), mapId, tempSet.getRealX(), tempSet.getRealY(), tempSet.getRealZ());
				ArrayList<String> sensors = SetSensorAssociationLocalDBHelper.
						getSingletonInstance(context).getListOfSensorsAssosiatedWithSet(tempSet.getId());
				for(String sensorId: sensors){
					MapSensorAssociationDBHelper.getSingletoneInstance(context).
					associateSensorWithMap(sensorId, tempSet.getRealX(), tempSet.getRealY(), tempSet.getRealZ());
				}
			}
		}

	}

	public void associateSetWithMap(String SetID,String MapID,float x,float y,float z){
		ContentValues values=new ContentValues();
		values.put(SETIDCOL, SetID);
		values.put(MAPIDCOL, MapID);
		values.put(XCOL, x);
		values.put(YCOL, y);
		values.put(ZCOL, z);

		try{
			long success = getWritableDatabase().insert(TABLE_NAME, null, values);
			if(success == -1){
				int res = getWritableDatabase().update(TABLE_NAME, values, SETIDCOL + "= '"
						+ SetID + "'",null);
				Log.i("z","in ass z = " + res);
			}
		}catch(Exception sqle){
			Log.i("sql","already exists");
		}

		SetListModel.getSingletonInstance().addAssociateSettoMap(SetID,MapID,x,y,z);

	}

	public void removeAssociation(String setId){
		getWritableDatabase().delete(TABLE_NAME, SETIDCOL + " = '" + setId + "'", null);
	}

	public void ReplicateAllMapAndSensorAssociations(){


		String query="SELECT * FROM " + TABLE_NAME + ";";

		Cursor cursor = getWritableDatabase().rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			do{

				SetListModel.getSingletonInstance().addAssociateSettoMap(cursor.getString(0), cursor.getString(1),cursor.getFloat(2),cursor.getFloat(3),cursor.getFloat(4));
			}while(cursor.moveToNext());
		}	

	}

	public Coords getCoordsForSet(String setId){
		String query="SELECT * FROM " + TABLE_NAME + " WHERE " +  SETIDCOL + " = " + "'" + setId + "'" +  ";";
		Coords tempCoords = null;
		Cursor cursor = getWritableDatabase().rawQuery(query, null);
		if(cursor.moveToFirst()){
			tempCoords =  new Coords(cursor.getFloat(2),cursor.getFloat(3),cursor.getFloat(4));
		}
		return tempCoords;
	}

	public ArrayList<String> getListOfSensorsAssosiatedWithMap(String mapId){
		String query="SELECT * FROM " + TABLE_NAME + " WHERE " +  MAPIDCOL + " = " + "'" + mapId + "'" +  ";";
		ArrayList<String> setIds = new ArrayList<String>();		
		Cursor cursor = getWritableDatabase().rawQuery(query, null);
		if(cursor.moveToFirst()){
			do{
				setIds.add(cursor.getString(0));
			}while(cursor.moveToNext());
		}
		return setIds;
	}

	public boolean isPlotted(String setId){
		String query="SELECT * FROM " + TABLE_NAME + " WHERE " +  SETIDCOL + " = " + "'" + setId + "'" +  ";";
		Cursor cursor = getWritableDatabase().rawQuery(query, null);
		if(cursor.getCount() > 0)
			return true;
		else 
			return false;
	}

	public static String getCreateMapSetAssTableQuery() {
		return CREATE_MAP_SET_ASS_TABLE_QUERY;
	}




}
