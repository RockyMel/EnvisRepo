package com.envisprototype.LocalDBHelper;


import com.envisprototype.model.sensor.SensorListModel;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SetSensorAssociationLocalDBHelper extends SQLiteOpenHelper {
	
	private static SetSensorAssociationLocalDBHelper singletonInstance;

	private static final String DBNAME="EnvisDB.db";
	private static final int VERSION=1;

	private static final String TABLE_NAME="SensorSetAssociation";
	private static final String SENSORIDCOL="SensorID";
	private static final String SETIDCOL="SetID";

	

	
	public SetSensorAssociationLocalDBHelper(Context context) {
		// TODO Auto-generated constructor stub

		super(context.getApplicationContext(),DBNAME,null,VERSION);
		
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String queryString = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY,%s TEXT);", TABLE_NAME,SENSORIDCOL,
				SETIDCOL);
		db.execSQL(queryString);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	
	public void associateSensorWithSet(String SensorID,String SetID){
		ContentValues values=new ContentValues();
		values.put(SENSORIDCOL, SensorID);
		values.put(SETIDCOL, SetID);
		getWritableDatabase().insert(TABLE_NAME, null, values);
		
		SensorListModel.getSingletonInstance().addAssociateSensortoSet(SensorID,SetID);
		
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
	
}
