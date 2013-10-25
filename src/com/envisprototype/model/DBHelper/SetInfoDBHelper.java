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

import com.envisprototype.model.set.SetInterface;

public class SetInfoDBHelper {


	final static String nameSpace = "http://api.webservice.envis.com";  
	final static String endPoint = "http://115.146.94.162:8080/EnvisAWS/services/DataProvider?wsdl";  


	public static void addSet(SetInterface set){

		//String[] args = { set.getId(),set.getName(),set.getLocation().getLongitude()+"",set.getLocation().getLongitude()+"",set.getNotes()};


		String methodName = "addSets";
		String soapAction = nameSpace + "/" + methodName;

		//Boolean response;

		SoapObject rpc = new SoapObject(nameSpace, methodName);   
		//PropertyInfo pi = new PropertyInfo();
		//Log.i("brand", newsensor.getBrand());
		//String sensor_info=newsensor.getId()+";"+newsensor.getType()+";"+newsensor.getName()+";"+newsensor.getBrand()+";"+"active"+";"+newsensor.getLocation().getLongitude() + "" +";"+newsensor.getLocation().getLatitude() + ""+";"+newsensor.getNotes()+";"+"END";
		//Log.i("testing",sensor_info);
		rpc.addProperty("setInfos", set.getId());
		rpc.addProperty("setInfos", set.getName());
		rpc.addProperty("setInfos", set.getLocation().getLongitude()+"");
		rpc.addProperty("setInfos", set.getLocation().getLatitude()+"");
		rpc.addProperty("setInfos", set.getNotes());



		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
		envelope.setOutputSoapObject(rpc);  

		HttpTransportSE transport = new HttpTransportSE(endPoint); 

		try {
			transport.call(soapAction, envelope);
			SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			System.out.println(" -----  "+resultsRequestSOAP.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		Log.i("SETDBHELPER", "done");




	}





}
