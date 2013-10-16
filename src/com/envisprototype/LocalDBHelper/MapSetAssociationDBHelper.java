package com.envisprototype.LocalDBHelper;

import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetListModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MapSetAssociationDBHelper extends SQLiteOpenHelper{
	private static MapSetAssociationDBHelper singletonInstance;
	//class to store events "list" works better with array adapter(which needs a list)
	//private SensorListInterface sensorModel=SensorListModel.getSingletonInstance();
	
	private static final String DBNAME="EnvisDB.db";
	private static final int VERSION=1;
	
	private static final String TABLE_NAME="MapSetAssociation";
	private static final String SETIDCOL="SetID";
	private static final String MAPIDCOL="MapID";
	private static final String XCOL="X";
	private static final String YCOL="Y";
	private static final String ZCOL="Z";
	
	public MapSetAssociationDBHelper(Context context) {
		// TODO Auto-generated constructor stub

		super(context.getApplicationContext(),DBNAME,null,VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String queryString = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY,%s TEXT,%f FLOAT,%f FLOAT,%f FLOAT);", TABLE_NAME,SETIDCOL,
				MAPIDCOL,XCOL,YCOL,ZCOL);
		db.execSQL(queryString);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void associateSetWithMap(String SetID,String MapID,float x,float y,float z){
		ContentValues values=new ContentValues();
		values.put(SETIDCOL, SetID);
		values.put(MAPIDCOL, MapID);
		values.put(XCOL, x);
		values.put(YCOL, y);
		values.put(ZCOL, z);

		
		getWritableDatabase().insert(TABLE_NAME, null, values);
		
	SetListModel.getSingletonInstance().addAssociateSettoMap(SetID,MapID,x,y,z);
		
	}
	
	public void ReplicateAllSetAndSensorAssociations(){
		

		String query="SELECT * FROM " + TABLE_NAME + ";";
		
		Cursor cursor = getWritableDatabase().rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			do{
		
				SetListModel.getSingletonInstance().addAssociateSettoMap(cursor.getString(0), cursor.getString(1),cursor.getFloat(2),cursor.getFloat(3),cursor.getFloat(4));
			}while(cursor.moveToNext());
		}	
		
	}
}
