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

public class SensorReadingDBHelper {
	final static String nameSpace = "http://api.webservice.envis.com";  
	final static String endPoint = "http://rmitenvis-env.elasticbeanstalk.com/services/DataProvider?wsdl";  


	

	public static String getDataReadingSensorByHisTimeJSON(String SensorID,String datefrom,String dateto){
		String methodName = "getDataReadingSensorByHisTime";
		String soapAction = nameSpace + "/" + methodName;

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
}
