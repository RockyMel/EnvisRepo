package com.envisprototype.LocalDBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetListModel;
import com.envisprototype.view.processing.SensorSet;


public class MapSensorAssociationDBHelper extends SQLiteOpenHelper{
	private static MapSensorAssociationDBHelper singletonInstance;
	//class to store events "list" works better with array adapter(which needs a list)
	//private SensorListInterface sensorModel=SensorListModel.getSingletonInstance();
	
	
	private static final String TABLE_NAME="MapSensorAssociation";
	private static final String SENSORIDCOL="SensorID";
	private static final String XCOL="X";
	private static final String YCOL="Y";
	private static final String ZCOL="Z";
	private static final String CREATE_MAP_SENSOR_ASS_TABLE_QUERY = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY,%s FLOAT,%s FLOAT,%s FLOAT);", TABLE_NAME,SENSORIDCOL,
			XCOL,YCOL,ZCOL);


	private Context context;
	
	private MapSensorAssociationDBHelper(Context context) {
		// TODO Auto-generated constructor stub

		super(context.getApplicationContext(),EnvisDBAdapter.getDbname(),null,EnvisDBAdapter.getVersion());
		this.context = context;
	}
	
	public static MapSensorAssociationDBHelper getSingletoneInstance(Context context){
		if(singletonInstance == null)
			singletonInstance = new MapSensorAssociationDBHelper(context);
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

	
	public void associateEnvisSensorsWithMap( HashMap<String,SensorSet> envisSensors){
		Iterator iterator = envisSensors.keySet().iterator();
		ContentValues values=new ContentValues();
		while(iterator.hasNext()){
			SensorSet tempSet = envisSensors.get(iterator.next());
			associateSensorWithMap(tempSet.getId(), tempSet.getRealX(), tempSet.getRealY(), tempSet.getRealZ());
			ArrayList<String> associatedSensorsIds = SetSensorAssociationLocalDBHelper.getSingletonInstance(context).getListOfSensorsAssosiatedWithSet(tempSet.getId());
			for(String sensorId: associatedSensorsIds){
				SensorInterface sensorToPlot = SensorLocalDBHelper.getSingletonInstance(context).findSensorById(sensorId);
				sensorToPlot.setX(tempSet.getRealX());
				sensorToPlot.setY(tempSet.getRealY());
				sensorToPlot.setZ(tempSet.getRealZ());
				//SensorLocalDBHelper.getSingletonInstance(context).editSensor(sensorToPlot);
				associateSensorWithMap(sensorId, tempSet.getRealX(), tempSet.getRealY(), tempSet.getRealZ());
			}
		}
		
	}
	
	public void associateSensorWithMap(String sensorId,float x,float y,float z){
		ContentValues values=new ContentValues();
		values.put(SENSORIDCOL, sensorId);
		values.put(XCOL, x);
		values.put(YCOL, y);
		values.put(ZCOL, z);
		
		long success = getWritableDatabase().insert(TABLE_NAME, null, values);
		if(success == -1){
			int res = getWritableDatabase().update(TABLE_NAME, values, SENSORIDCOL + "= '"
					+ sensorId + "'",null);
			Log.i("z","in ass z = " + res);
		}
		
	SensorListModel.getSingletonInstance().addAssociateSensortoMap(sensorId,x,y,z);
		
	}
	
	public void ReplicateAllMapAndSensorAssociations(){
		

		String query="SELECT * FROM " + TABLE_NAME + ";";
		
		Cursor cursor = getWritableDatabase().rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			do{
		
				SensorListModel.getSingletonInstance().addAssociateSensortoMap(cursor.getString(0), cursor.getFloat(1),cursor.getFloat(2),cursor.getFloat(3));
				
			}while(cursor.moveToNext());
		}	
		
	}
	

	public static String getCreateMapSetAssTableQuery() {
		return CREATE_MAP_SENSOR_ASS_TABLE_QUERY;
	}


	
	
}
