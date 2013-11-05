package com.envisprototype.model.DBHelper;

import java.io.IOException;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.LocalDBHelper.MapSetAssociationDBHelper;
import com.envisprototype.LocalDBHelper.SensorLocalDBHelper;
import com.envisprototype.LocalDBHelper.SetLocalDBHelper;
import com.envisprototype.LocalDBHelper.SetSensorAssociationLocalDBHelper;
import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapModel;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetModel;

public class SynchronizeWithCloud {

	final static String nameSpace = "http://api.webservice.envis.com";  
	final static String endPoint = "http://115.146.92.166/EnvisAWS/services/DataProvider?wsdl";  


	public static void SyncSets(Context context){

		//String[] args = { set.getId(),set.getName(),set.getLocation().getLongitude()+"",set.getLocation().getLongitude()+"",set.getNotes()};


		String methodName = "getAllSet";
		String soapAction = nameSpace + "/" + methodName;



		SoapObject rpc = new SoapObject(nameSpace, methodName);   



		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);  

		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		SoapPrimitive resultsRequestSOAP = null;
		try {
			transport.call(soapAction, envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			System.out.println(" -----  "+resultsRequestSOAP.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		if(resultsRequestSOAP!= null && resultsRequestSOAP.toString()!=null)
		{
			JSONObject obj = null;
			try {
				obj = new JSONObject(resultsRequestSOAP.toString());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int i=1;
			while(true)
			{
				String id=null;
				String name=null;
				double longitude;
				double latitude;
				String notes=null;

				try{
					if(obj.getJSONArray(i+"")!=null){
						id = obj.getJSONArray(i+"").getString(0);
						name = obj.getJSONArray(i+"").getString(1);
						longitude = Double.parseDouble(obj.getJSONArray(i+"").getString(2));
						latitude = Double.parseDouble(obj.getJSONArray(i+"").getString(3));
						notes = obj.getJSONArray(i+"").getString(4);
						SetInterface newset = new SetModel();
						newset.setId(id);
						newset.setName(name);
						Location location=new Location(LocationManager.NETWORK_PROVIDER);
						location.setLongitude(longitude);
						location.setLatitude(latitude);
						newset.setLocation(location);
						newset.setNotes(notes);
						SetLocalDBHelper.getSingletonInstance(context).addSet(newset);
					}
					else
						break;
					i++;

				}
				catch(Exception e){
					e.printStackTrace();
					break;
				}
			}
		}


	}

	public static void SyncSensors(Context context){

		//String[] args = { set.getId(),set.getName(),set.getLocation().getLongitude()+"",set.getLocation().getLongitude()+"",set.getNotes()};


		String methodName = "getAllSensor";
		String soapAction = nameSpace + "/" + methodName;



		SoapObject rpc = new SoapObject(nameSpace, methodName);   



		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);  

		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		SoapPrimitive resultsRequestSOAP = null;
		try {
			transport.call(soapAction, envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			System.out.println(" -----  "+resultsRequestSOAP.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		if(resultsRequestSOAP != null && resultsRequestSOAP.toString()!=null)
		{
			JSONObject obj = null;
			try {
				obj = new JSONObject(resultsRequestSOAP.toString());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int i=1;
			while(true)
			{
				String id=null;
				String name=null;
				String brand=null;
				int type;
				String notes=null;
				double maxValue, minValue;

				try{
					if(obj.getJSONArray(i+"")!=null){
						id = obj.getJSONArray(i+"").getString(0);
						type = obj.getJSONArray(i+"").getInt(1);
						name = obj.getJSONArray(i+"").getString(2);
						brand = obj.getJSONArray(i+"").getString(3);
						notes = obj.getJSONArray(i+"").getString(5);
						//6, 7 - max min values it's a varchar 6- max 7 - min
						maxValue = Double.parseDouble(obj.getJSONArray(i+"").getString(6));
						minValue = Double.parseDouble(obj.getJSONArray(i+"").getString(7));
						
						SensorInterface newsensor = new SensorModel();
						newsensor.setId(id);
						newsensor.setName(name);
						newsensor.setBrand(brand);
						newsensor.setType(type);
						newsensor.setNotes(notes);
						newsensor.setMaxValue(maxValue);
						newsensor.setMinValue(minValue);
						//if(type!=7)
							SensorLocalDBHelper.getSingletonInstance(context).addSensor(newsensor);
					}
					else
						break;
					i++;

				}
				catch(Exception e){
					break;
				}
			}
		}


	}

	public static void SyncMaps(Context context){

		//String[] args = { set.getId(),set.getName(),set.getLocation().getLongitude()+"",set.getLocation().getLongitude()+"",set.getNotes()};


		String methodName = "getAllMap";
		String soapAction = nameSpace + "/" + methodName;



		SoapObject rpc = new SoapObject(nameSpace, methodName);   



		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);  

		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		SoapPrimitive resultsRequestSOAP = null;
		try {
			transport.call(soapAction, envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			System.out.println(" -----  "+resultsRequestSOAP.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		if(resultsRequestSOAP != null && resultsRequestSOAP.toString()!=null)
		{
			JSONObject obj = null;
			try {
				obj = new JSONObject(resultsRequestSOAP.toString());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int i=1;
			while(true)
			{
				String id=null;
				String name=null;
				float z;
				String notes=null;
				double longitude;
				double latitude;
				String xcoor;
				String ycoor;
				

				try{
					if(obj.getJSONArray(i+"")!=null){
						id = obj.getJSONArray(i+"").getString(0);
						z = (float)obj.getJSONArray(i+"").getDouble(1);
						longitude = (float)obj.getJSONArray(i+"").getDouble(2);
						latitude = (float)obj.getJSONArray(i+"").getDouble(3);
						name = obj.getJSONArray(i+"").getString(4);
						notes = obj.getJSONArray(i+"").getString(5);
						xcoor = obj.getJSONArray(i+"").getString(6);
						ycoor = obj.getJSONArray(i+"").getString(7);
						
						MapInterface newmap = new MapModel();
						newmap.setId(id);
						newmap.setzCoordinate(z);
						StringTokenizer Xcoor = new StringTokenizer(xcoor,",");
						
						//Log.i("db helper coor", "the whole line is " + cursor.getString(4));
						Float abc= null;

						while(Xcoor.hasMoreElements()){
						
							abc = Float.parseFloat((String) Xcoor.nextElement());
							newmap.getRealCoordinates().getCoorX().add(abc);
							//Log.i("db helper coor", Integer.toString(i) + " ##### " + abc );
							
						}
						//Log.i("db helper coor", tempmap.getRealCoordinates().getXCoorString());
						StringTokenizer Ycoor = new StringTokenizer(ycoor,",");
						while(Ycoor.hasMoreElements()){
							newmap.getRealCoordinates().getCoorY().add(Float.parseFloat((String) Ycoor.nextElement()));
							
							
						}
						Location location=new Location(LocationManager.NETWORK_PROVIDER);
						location.setLongitude(longitude);
						location.setLatitude(latitude);
						newmap.setLocation(location);
						
						//newmap.setRealCoordinates(realCoordinates);
						newmap.setName(name);
						newmap.setNotes(notes);
						MapLocalDBHelper.getSingletonInstance(context).addMap(newmap);
					}
					else
						break;
					i++;

				}
				catch(Exception e){
					break;
				}
			}
		}


	}

	public static void SyncSetAndSensorAssociation(Context context){

		//String[] args = { set.getId(),set.getName(),set.getLocation().getLongitude()+"",set.getLocation().getLongitude()+"",set.getNotes()};


		String methodName = "getAllAssociationSetAndSensor";
		String soapAction = nameSpace + "/" + methodName;



		SoapObject rpc = new SoapObject(nameSpace, methodName);   



		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);  

		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		SoapPrimitive resultsRequestSOAP = null;
		try {
			transport.call(soapAction, envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			System.out.println(" -----  "+resultsRequestSOAP.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		if(resultsRequestSOAP != null && resultsRequestSOAP.toString()!=null)
		{
			JSONObject obj = null;
			try {
				obj = new JSONObject(resultsRequestSOAP.toString());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int i=1;
			while(true)
			{
				String sensorid=null;
				String setid=null;


				try{
					if(obj.getJSONArray(i+"")!=null){
						sensorid = obj.getJSONArray(i+"").getString(0);
						setid = obj.getJSONArray(i+"").getString(1);
						SetSensorAssociationLocalDBHelper.getSingletonInstance(context).associateSensorWithSet(sensorid, setid);
					}
					else
						break;
					i++;

				}
				catch(Exception e){
					break;
				}
			}
		}


	}

	public static void SyncSetAndMapAssociation(Context context){

		//String[] args = { set.getId(),set.getName(),set.getLocation().getLongitude()+"",set.getLocation().getLongitude()+"",set.getNotes()};


		String methodName = "getAllAssociationMapAndSet";
		String soapAction = nameSpace + "/" + methodName;



		SoapObject rpc = new SoapObject(nameSpace, methodName);   



		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);  

		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		SoapPrimitive resultsRequestSOAP = null;
		try {
			transport.call(soapAction, envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			System.out.println(" -----  "+resultsRequestSOAP.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		if(resultsRequestSOAP != null && resultsRequestSOAP.toString()!=null)
		{
			JSONObject obj = null;
			try {
				obj = new JSONObject(resultsRequestSOAP.toString());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int i=1;
			while(true)
			{
				String mapid=null;
				String setid=null;
				float x;
				float y;
				float z;


				try{
					if(obj.getJSONArray(i+"")!=null){
						setid = obj.getJSONArray(i+"").getString(0);
						mapid = obj.getJSONArray(i+"").getString(1);
						x = (float)obj.getJSONArray(i+"").getDouble(2);
						y = (float)obj.getJSONArray(i+"").getDouble(3);
						z = (float)obj.getJSONArray(i+"").getDouble(4);

						MapSetAssociationDBHelper.getSingletoneInstance(context).associateSetWithMap(setid, mapid, x, y, z);
					}
					else
						break;
					i++;

				}
				catch(Exception e){
					break;
				}
			}
		}


	}


}
