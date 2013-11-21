package com.envisprototype.model.DBHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.envisprototype.model.LocalDBHelper.MapSensorAssociationDBHelper;
import com.envisprototype.model.LocalDBHelper.SetSensorAssociationLocalDBHelper;
import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.view.processing.SensorSet;



public class MapInfoDBHelper {
	final static String nameSpace = "http://api.webservice.envis.com";  
	final static String endPoint = "http://115.146.92.166/EnvisAWS/services/DataProvider?wsdl";  

	//115.146.92.166/EnvisAWS/services/DataProvider/addMap?mapInfos=mapid&mapInfos=23.2&mapInfos=longitude&mapInfos=latitude&mapInfos=name&mapInfos=notes&mapInfos=x,x,x,x&mapInfos=y,y,y,y


	public static boolean addMap(MapInterface newmap){
		String methodName = "addMap";
		String soapAction = nameSpace + "/" + methodName;
		SoapObject rpc = new SoapObject(nameSpace, methodName);   

		rpc.addProperty("mapInfos", newmap.getId());
		rpc.addProperty("mapInfos", newmap.getzCoordinate()+"");
	
		rpc.addProperty("mapInfos", newmap.getLocation().getLongitude()+"");
		rpc.addProperty("mapInfos", newmap.getLocation().getLatitude()+"");
		rpc.addProperty("mapInfos", newmap.getName());
		rpc.addProperty("mapInfos", newmap.getNotes());
		String x = newmap.getRealCoordinates().getXCoorString();
		String y = newmap.getRealCoordinates().getYCoorString();
		rpc.addProperty("mapInfos", x);
		rpc.addProperty("mapInfos", y);




		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);  

		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		SoapPrimitive resultsRequestSOAP=null;
		try {
			transport.call(soapAction, envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			System.out.println(resultsRequestSOAP.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		Log.i("DBHELPER", resultsRequestSOAP.toString());
		
		if( resultsRequestSOAP.toString().equals("true"))
				return true;
		else
			return false;


	}

	
	public static boolean editMap(MapInterface newmap){
		String methodName = "editMap";
		String soapAction = nameSpace + "/" + methodName;
		SoapObject rpc = new SoapObject(nameSpace, methodName);   

		rpc.addProperty("mapInfos", newmap.getId());
		rpc.addProperty("mapInfos", newmap.getzCoordinate() + "");
	
		rpc.addProperty("mapInfos", newmap.getLocation().getLongitude() + "");
		rpc.addProperty("mapInfos", newmap.getLocation().getLatitude() + "");
		rpc.addProperty("mapInfos", newmap.getName());
		rpc.addProperty("mapInfos", newmap.getNotes());
		String x = newmap.getRealCoordinates().getXCoorString();
		String y = newmap.getRealCoordinates().getYCoorString();
		rpc.addProperty("mapInfos", x);
		rpc.addProperty("mapInfos", y);




		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);  

		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		SoapPrimitive resultsRequestSOAP=null;
		try {
			transport.call(soapAction, envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			System.out.println(resultsRequestSOAP.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		Log.i("DBHELPER", resultsRequestSOAP.toString());
		
		if( resultsRequestSOAP.toString().equals("true"))
				return true;
		else
			return false;


	}
	
	public static void plotOnMap(HashMap<String,SensorSet> envisSensors, String mapId, Context context){
		String methodName = "plotSetsOnMap";
		String soapAction = nameSpace + "/" + methodName;
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		String setId;
		Float setX, setY, setZ;
		Iterator iterator = envisSensors.keySet().iterator();
		ContentValues values=new ContentValues();
		while(iterator.hasNext()){
			SensorSet tempSet = envisSensors.get(iterator.next());
			if(!tempSet.isIfSensor()){
				setId = tempSet.getId(); 
				setX = tempSet.getRealX();
				setY = tempSet.getRealY();
				setZ = tempSet.getRealZ();
				ArrayList<String> sensors = SetSensorAssociationLocalDBHelper.
						getSingletonInstance(context).getListOfSensorsAssosiatedWithSet(tempSet.getId());
				rpc.addProperty("setAndMapInfos", setId);
				rpc.addProperty("setAndMapInfos", mapId);
			
				rpc.addProperty("setAndMapInfos", setX + "");
				rpc.addProperty("setAndMapInfos", setY+ "");
				rpc.addProperty("setAndMapInfos", setZ + "");

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
				envelope.setOutputSoapObject(rpc);

				HttpTransportSE transport = new HttpTransportSE(endPoint); 
				SoapPrimitive resultsRequestSOAP=null;
				try {
					transport.call(soapAction, envelope);
					resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
					System.out.println(resultsRequestSOAP.toString());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}

				Log.i("DBHELPER", resultsRequestSOAP.toString());
				for(String sensorId: sensors){
					MapSensorAssociationDBHelper.getSingletoneInstance(context).
					associateSensorWithMap(sensorId, tempSet.getRealX(), tempSet.getRealY(), tempSet.getRealZ());
					plotSensorOnMap(sensorId, setId, tempSet.getRealX(), tempSet.getRealY(), tempSet.getRealZ());
				}
			}
		}

	}
	
	public static boolean plotSensorOnMap(String sensorId, String setId, Float X, Float Y, Float Z){
		String methodName = "editAssociationSensorAndSet";
		String soapAction = nameSpace + "/" + methodName;
		SoapObject rpc = new SoapObject(nameSpace, methodName);   

		rpc.addProperty("dataInfos", sensorId);
		rpc.addProperty("dataInfos", setId);
		rpc.addProperty("dataInfos", X + "");
		rpc.addProperty("dataInfos", Y + "");
		rpc.addProperty("dataInfos", Z + "");

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);  

		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		SoapPrimitive resultsRequestSOAP=null;
		try {
			transport.call(soapAction, envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			System.out.println(resultsRequestSOAP.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		Log.i("DBHELPER", resultsRequestSOAP.toString());
		
		if( resultsRequestSOAP.toString().equals("true"))
				return true;
		else
			return false;


	}
	
	public static boolean unplotSetFromMap(String setId){
		String methodName = "unplotSetsOnMap";
		String soapAction = nameSpace + "/" + methodName;
		SoapObject rpc = new SoapObject(nameSpace, methodName);   

		rpc.addProperty("setID", setId);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);  

		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		SoapPrimitive resultsRequestSOAP=null;
		try {
			transport.call(soapAction, envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			System.out.println(resultsRequestSOAP.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		Log.i("DBHELPER", resultsRequestSOAP.toString());
		
		if( resultsRequestSOAP.toString().equals("true"))
				return true;
		else
			return false;


	}
	
	public static boolean deleteMap(String mapId){
		String methodName = "removeMap";
		String soapAction = nameSpace + "/" + methodName;
		SoapObject rpc = new SoapObject(nameSpace, methodName);   

		rpc.addProperty("mapID", mapId);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);  

		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		SoapPrimitive resultsRequestSOAP=null;
		try {
			transport.call(soapAction, envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			System.out.println(resultsRequestSOAP.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		Log.i("DBHELPER", resultsRequestSOAP.toString());
		
		if( resultsRequestSOAP.toString().equals("true"))
				return true;
		else
			return false;


	}
	
	
	public static String generateMapID(){
		String methodName = "generateMapID";
		String soapAction = nameSpace + "/" + methodName;
		SoapObject rpc = new SoapObject(nameSpace, methodName);   

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);  

		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		SoapPrimitive resultsRequestSOAP = null;
		try {
			transport.call(soapAction, envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			System.out.println(resultsRequestSOAP.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		//Log.i("DBHELPER", "done");
		return resultsRequestSOAP.toString();


	}

}
