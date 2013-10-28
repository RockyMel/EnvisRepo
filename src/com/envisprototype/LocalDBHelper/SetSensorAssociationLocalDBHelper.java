package com.envisprototype.LocalDBHelper;


import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.sensorSetAssosiation.SensorSetAssosiationModel;

public class SetSensorAssociationLocalDBHelper extends SQLiteOpenHelper {
	
	private static SetSensorAssociationLocalDBHelper singletonInstance;

	private static final String TABLE_NAME="SensorSetAssociation";
	private static final String SENSORIDCOL="SensorID";
	private static final String SETIDCOL="SetID";
	private static final String CREATE_SET_SENSOR_ASS_TABLE_QUERY = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY,%s TEXT);", TABLE_NAME,SENSORIDCOL,
			SETIDCOL);


	private Context context;
	

	
	private SetSensorAssociationLocalDBHelper(Context context) {
		// TODO Auto-generated constructor stub

		super(context.getApplicationContext(),EnvisDBAdapter.getDbname(),null,EnvisDBAdapter.getVersion());
		this.context = context;
	}
	
	public static SetSensorAssociationLocalDBHelper getSingletonInstance(Context context){
		if(singletonInstance == null)
			singletonInstance = new SetSensorAssociationLocalDBHelper(context);
		return singletonInstance;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		EnvisDBAdapter.getSingletonInstance(context).onCreate(db);
		//db.execSQL(queryString);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	
	public void associateSensorWithSet(String SensorID,String SetID){
		ContentValues values=prepareValues(SensorID, SetID);
		getWritableDatabase().insert(TABLE_NAME, null, values);
		
		SensorListModel.getSingletonInstance().addAssociateSensortoSet(SensorID,SetID);
		
	}
	
	
	public void editSetSensorAss(String SensorID,String SetID) {
		// TODO Auto-generated method stub
		
		ContentValues values = prepareValues(SensorID, SetID);
		getWritableDatabase().update(TABLE_NAME, values, SENSORIDCOL + "= \""
				+ SensorID + "\"",null);
	
	}
	
	private ContentValues prepareValues(String SensorID,String SetID) {
		ContentValues values=new ContentValues();
		values.put(SENSORIDCOL, SensorID);
		values.put(SETIDCOL, SetID);
		getWritableDatabase().insert(TABLE_NAME, null, values);
		return values;
	}
	
	public ArrayList<String> getListOfSensorsAssosiatedWithSet(String setId){
		String query="SELECT " + SENSORIDCOL + " FROM " + TABLE_NAME + " WHERE " +  SETIDCOL + " = " + "'" + setId + "'" +  ";";
		ArrayList<String> setIds = new ArrayList<String>();		
		Cursor cursor = getWritableDatabase().rawQuery(query, null);
		if(cursor.moveToFirst()){
			do{
				setIds.add(cursor.getString(0));
			}while(cursor.moveToNext());
		}
		return setIds;
	}
	
	public void ReplicateAllSetAndSensorAssociations(){
	

		String query="SELECT * FROM " + TABLE_NAME + ";";
		
		Cursor cursor = getWritableDatabase().rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			do{
		
				SensorListModel.getSingletonInstance().addAssociateSensortoSet(cursor.getString(0), cursor.getString(1));
			}while(cursor.moveToNext());
		}	
		
	}

	public static String getCreateSetSensorAssTableQuery() {
		return CREATE_SET_SENSOR_ASS_TABLE_QUERY;
	}

	public void removeAssociation(String id) {
		// TODO Auto-generated method stub
		getWritableDatabase().delete(TABLE_NAME,SENSORIDCOL+"='" + id +"'", null);
		
	}

	
}
