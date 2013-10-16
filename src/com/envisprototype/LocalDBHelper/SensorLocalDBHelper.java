package com.envisprototype.LocalDBHelper;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.sensor.SensorModel;

public class SensorLocalDBHelper extends SQLiteOpenHelper implements SensorListInterface{
	private static SensorLocalDBHelper singletonInstance;
	//class to store events "list" works better with array adapter(which needs a list)
	private SensorListInterface sensorModel=SensorListModel.getSingletonInstance();
	
	private static final String DBNAME="EnvisDB.db";
	private static final int VERSION=1;
	
	private static final String TABLE_NAME="Sensors";
	private static final String IDCOL="SensorID";
	private static final String NAMECOL="SensorName";
	private static final String TYPECOL="Type";
	private static final String BRANDCOL="Brand";
	private static final String NOTESCOL="Notes";
	
	
	
	
	public SensorLocalDBHelper(Context context) {
		// TODO Auto-generated constructor stub

		super(context.getApplicationContext(),DBNAME,null,VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String queryString = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY,%s TEXT,%s INT,%s TEXT,%s TEXT);", TABLE_NAME,
				IDCOL,NAMECOL,TYPECOL,BRANDCOL,NOTESCOL);
		db.execSQL(queryString);
		
	}


	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		//NOT IMPLEMENTED
	}

	public static SensorListInterface getSingletonInstance(Context context) {
		if(singletonInstance==null)
			singletonInstance=new SensorLocalDBHelper(context);

		return singletonInstance;
	}

	@Override
	public void addSensor(SensorInterface sensor) {
		// TODO Auto-generated method stub
		ContentValues values=new ContentValues();
		values.put(IDCOL, sensor.getId());
		values.put(NAMECOL, sensor.getName());
		values.put(TYPECOL, sensor.getType());
		values.put(BRANDCOL, sensor.getBrand());
		values.put(NOTESCOL, "samplenote");
		
		getWritableDatabase().insert(TABLE_NAME, null, values);
	
		sensorModel.addSensor(sensor);
	}

	@Override
	public void removeSensor(SensorInterface sensor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SensorInterface findSensorById(String Id) {
		// NOT IMPLEMENTED
		return null;
	}

	@Override
	public List<SensorInterface> getSensorList() {
		// TODO Auto-generated method stub
		List<SensorInterface> temp=new ArrayList<SensorInterface>();
		
		
		String query="SELECT * FROM " + TABLE_NAME + ";";
		
		Cursor cursor = getWritableDatabase().rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			do{
				SensorInterface tempsensor=new SensorModel();
				tempsensor.setId(cursor.getString(0));
				tempsensor.setName(cursor.getString(1));
				tempsensor.setType(cursor.getInt(2));
				tempsensor.setBrand(cursor.getString(3));
				tempsensor.setNotes(cursor.getString(4));
				temp.add(tempsensor);
			}while(cursor.moveToNext());
		}	
		
		return temp;
	
		}

	@Override
	public boolean isSensorListEmpty() {
		// NOT IMPLEMENTED
		return false;
	}

	@Override
	public List getSensorListBySetID(String ID) {
		// NOT IMPLEMENTED
		return null;
	}

	@Override
	public List getSensorIDListByType(int type) {
		// NOT IMPLEMENTED
		return null;
	}

	
	public void ReplicateSensorList() {
		// TODO Auto-generated method stub

		List<SensorInterface> thesensor = getSensorList();
		SensorListModel.getSingletonInstance().setSensorList(thesensor);
		
	}

	@Override
	public void setSensorList(List<SensorInterface> thesensor) {
		// NOT IMPLEMENTED
		
	}

	@Override
	public void addAssociateSensortoSet(String sensorID, String setID) {
		// NOT IMPLEMENTED
		
	}

	
}