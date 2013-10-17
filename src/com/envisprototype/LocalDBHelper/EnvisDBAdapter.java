package com.envisprototype.LocalDBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EnvisDBAdapter extends SQLiteOpenHelper{
	
	private static final String DBNAME="EnvisDB.db";
	private static final int VERSION=1;
	private static EnvisDBAdapter singletonInstance;
	private Context context;
	
	public static EnvisDBAdapter getSingletonInstance(Context context){
		if(singletonInstance == null)
			singletonInstance = new EnvisDBAdapter(context);
		return singletonInstance;
	}
	
	private EnvisDBAdapter(Context context) {
		super(context.getApplicationContext(),DBNAME,null,VERSION);
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static String getDbname() {
		return DBNAME;
	}
	public static int getVersion() {
		return VERSION;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String queryToCreate = MapLocalDBHelper.getCreateMapTableQuery();
		db.execSQL(queryToCreate);
		queryToCreate = MapSetAssociationDBHelper.getCreateMapSetAssTableQuery();
		db.execSQL(queryToCreate);
		queryToCreate = SensorLocalDBHelper.getCreateSensorTableQuery();
		db.execSQL(queryToCreate);
		queryToCreate = SetSensorAssociationLocalDBHelper.getCreateSetSensorAssTableQuery();
		db.execSQL(queryToCreate);
		queryToCreate = SetLocalDBHelper.getCreateSetTableQuery();
		db.execSQL(queryToCreate);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void replecateDB(){
		MapLocalDBHelper.getSingletonInstance(context).ReplicateMapList();
		MapSetAssociationDBHelper.getSingletoneInstance(context).ReplicateAllMapAndSensorAssociations();
		SensorLocalDBHelper.getSingletonInstance(context).ReplicateSensorList();
		SetLocalDBHelper.getSingletonInstance(context).ReplicateSetList();
		SetSensorAssociationLocalDBHelper.getSingletonInstance(context).ReplicateAllSetAndSensorAssociations();
	}
	
	
}
