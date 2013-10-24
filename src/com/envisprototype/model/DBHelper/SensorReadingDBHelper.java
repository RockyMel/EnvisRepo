package com.envisprototype.model.DBHelper;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class SensorReadingDBHelper {
	final static String nameSpace = "http://api.webservice.envis.com";  
	final static String endPoint = "http://rmitenvis-env.elasticbeanstalk.com/services/DataProvider?wsdl";  




	public static String getDataReadingSensorByHisTimeJSON(String SensorID,String datefrom,String dateto){
		String methodName = "getDataReadingSensorByHisTime";
		String soapAction = nameSpace + "/" + methodName;
		
		Log.i("dateFROM", datefrom);
		Log.i("dateTO", dateto);
		
		//Boolean response;

		SoapObject rpc = new SoapObject(nameSpace, methodName);   
		//PropertyInfo pi = new PropertyInfo(); 	
		//Log.i("brand", newsensor.getBrand());
		//String sensor_info=newsensor.getId()+";"+newsensor.getType()+";"+newsensor.getName()+";"+newsensor.getBrand()+";"+"active"+";"+newsensor.getLocation().getLongitude() + "" +";"+newsensor.getLocation().getLatitude() + ""+";"+newsensor.getNotes()+";"+"END";
		//Log.i("testing",sensor_info);
		rpc.addProperty("SensorID", SensorID);
		rpc.addProperty("datefrom", datefrom);
		rpc.addProperty("dateto", dateto);
		rpc.addProperty("dataType", 1);



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

		Log.i("DBHELPER", "done");
		return resultsRequestSOAP.toString();


	}
	public static String getDataReadingBySensorIDJSON(String SensorID,Context context){
		Log.i("idddddd", SensorID);
		String methodName = "getDataReadingBySensorID";
		String soapAction = nameSpace + "/" + methodName;

		//Boolean response;

		SoapObject rpc = new SoapObject(nameSpace, methodName);   
	
		rpc.addProperty("sensorID", SensorID);
		rpc.addProperty("dataFormat", 1);



		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);
		String temp = null;
		if(isConnectingToInternet(context))
		{
			HttpTransportSE transport = new HttpTransportSE(endPoint); 
			SoapPrimitive resultsRequestSOAP = null;
			try {
				transport.call(soapAction, envelope);
				resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
				temp = resultsRequestSOAP.toString();
				System.out.println(resultsRequestSOAP.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
		}
		else
		{
			temp = "Not";
			Log.i("NETCONNECTION1", "NO");
			Log.i("NETCONNECTION2", temp);
			
		}
		Log.i("DBHELPER", "done");
		return temp;


	}

	public static boolean isConnectingToInternet(Context _context){
		ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) 
		{
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) 
				for (int i = 0; i < info.length; i++) 
					if (info[i].getState() == NetworkInfo.State.CONNECTED)
					{
						return true;
					}

		}
		return false;
	}
	public static String getSensorReadingByRealTime(String sensorId, Context context ,int dataType) {
		Log.i("idddddd", sensorId);
		String methodName = "getSensorReadingByRealTime";
		String soapAction = nameSpace + "/" + methodName;

		//Boolean response;

		SoapObject rpc = new SoapObject(nameSpace, methodName);   
	
		rpc.addProperty("sensorID", sensorId);
		rpc.addProperty("dataType", 1);



		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);
		String temp = null;
		if(isConnectingToInternet(context))
		{
			HttpTransportSE transport = new HttpTransportSE(endPoint); 
			SoapPrimitive resultsRequestSOAP = null;
			try {
				transport.call(soapAction, envelope);
				resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
				temp = resultsRequestSOAP.toString();
				System.out.println(resultsRequestSOAP.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
		}
		else
		{
			temp = "Not";
			Log.i("NETCONNECTION1", "NO");
			Log.i("NETCONNECTION2", temp);
			
		}
		Log.i("DBHELPER", "done");
		return temp;
	}

}
