package com.envisprototype.model.LocalDBHelper;

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
import com.envisprototype.model.sensor.SensorListInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListInterface;
import com.envisprototype.model.set.SetListModel;
import com.envisprototype.model.set.SetModel;

public class SetLocalDBHelper extends SQLiteOpenHelper implements SetListInterface{

	private static SetLocalDBHelper singletonInstance;
	//class to store events "list" works better with array adapter(which needs a list)
	private SetListInterface setModel=SetListModel.getSingletonInstance();
	private Context context;


	private static final String TABLE_NAME="Sets";
	private static final String IDCOL="SetID";
	private static final String NAMECOL="SetName";

	private static final String LONGITUDECOL="Longitude";
	private static final String LATITUDECOL="Latitude";
	private static final String NOTESCOL="Notes";
	
	private static final String CREATE_SET_TABLE_QUERY = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY,%s TEXT,%s DOUBLE,%s DOUBLE,%s TEXT);", TABLE_NAME,
			IDCOL,NAMECOL,LONGITUDECOL,LATITUDECOL,NOTESCOL);

	public SetLocalDBHelper(Context context) {
		// TODO Auto-generated constructor stub

		super(context.getApplicationContext(),EnvisDBAdapter.getDbname(),null,EnvisDBAdapter.getVersion());
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		EnvisDBAdapter.getSingletonInstance(context).onCreate(db);
		
		Log.i("setdb","in set oncreate");
		//db.execSQL(queryString);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}



	@Override
	public void addSet(SetInterface newset) {
		// TODO Auto-generated method stub
		ContentValues values= prepareValues(newset);
		Log.i("asd", "asddf");
		getWritableDatabase().insert(TABLE_NAME, null, values);

		setModel.addSet(newset);
	}
	
	public void editSet(SetInterface newset) {
		// TODO Auto-generated method stub
		
		ContentValues values = prepareValues(newset);
		getWritableDatabase().update(TABLE_NAME, values, IDCOL + "= \""
				+ newset.getId() + "\"",null);
	
		setModel.editSet(newset);
	}
	
	private ContentValues prepareValues(SetInterface newset) {
		ContentValues values=new ContentValues();
		values.put(IDCOL, newset.getId());
		values.put(NAMECOL, newset.getName());
		values.put(LONGITUDECOL, newset.getLocation().getLongitude());
		values.put(LATITUDECOL, newset.getLocation().getLatitude());
		values.put(NOTESCOL, newset.getNotes());
		return values;
	}

	@Override
	public void removeSet(SetInterface set) {
		// TODO Auto-generated method stub

	}

	@Override
	public SetInterface findSetById(String id) {
		// NOT IMPLEMENTED
		return null;
	}

	@Override
	public List<SetInterface> getSetList() {
		// TODO Auto-generated method stub
		List<SetInterface> temp=new ArrayList<SetInterface>();


		String query="SELECT * FROM " + TABLE_NAME + ";";
		
		Cursor cursor = getWritableDatabase().rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			do{
				SetInterface tempset=new SetModel();
				tempset.setId(cursor.getString(0));
				tempset.setName(cursor.getString(1));
				Location location =  new Location("");

				location.setLongitude(cursor.getDouble(2));
				location.setLatitude(cursor.getDouble(3));

				tempset.setLocation(location);
				
				temp.add(tempset);
			}while(cursor.moveToNext());
		}	

		return temp;
	}


	public static SetListInterface getSingletonInstance(Context context) {
		if(singletonInstance==null)
			singletonInstance=new SetLocalDBHelper(context);

		return singletonInstance;
	}

	
	public void ReplicateSetList() {
		// TODO Auto-generated method stub

		List<SetInterface> theset = getSetList();
		SetListModel.getSingletonInstance().setSetList(theset);
		
	}

	@Override
	public void setSetList(List<SetInterface> theset) {
		// NOT IMPLEMENTED
		
	}

	@Override
	public void addAssociateSettoMap(String setID, String mapID, float x,
			float y, float z) {
		// NOT IMPLEMENTED -- CHECK ASSOCIATION LOCAL DB HELPER
		
	}

	public static String getCreateSetTableQuery() {
		return CREATE_SET_TABLE_QUERY;
	}

	@Override
	public List<SetInterface> getSetListByIds(List<String> setIds) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
