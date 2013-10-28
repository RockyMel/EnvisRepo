package com.envisprototype.model.DBHelper;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

import com.envisprototype.model.sensor.SensorInterface;

public class SensorInfoDBHelper {
	final static String nameSpace = "http://api.webservice.envis.com";  
	final static String endPoint = "http://115.146.92.166/EnvisAWS/services/DataProvider?wsdl";  




	public static boolean addSensor(SensorInterface newsensor){
		String methodName = "addSensor";
		String soapAction = nameSpace + "/" + methodName;


		SoapObject rpc = new SoapObject(nameSpace, methodName);   
		Log.i("brand", newsensor.getBrand());
		//String sensor_info=newsensor.getId()+";"+newsensor.getType()+";"+newsensor.getName()+";"+newsensor.getBrand()+";"+"active"+";"+newsensor.getLocation().getLongitude() + "" +";"+newsensor.getLocation().getLatitude() + ""+";"+newsensor.getNotes()+";"+"END";
		//Log.i("testing",sensor_info);
		rpc.addProperty("sensorInfos", newsensor.getId());
		rpc.addProperty("sensorInfos", newsensor.getType());
		rpc.addProperty("sensorInfos", newsensor.getName());
		rpc.addProperty("sensorInfos", newsensor.getBrand());
		rpc.addProperty("sensorInfos", "active");
		rpc.addProperty("sensorInfos", newsensor.getNotes());



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

	public static String generateSensorID(){
		String methodName = "generateSensorID";
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


	public static void associateSensorWithSet(){

	}

}
