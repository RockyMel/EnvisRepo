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

public class SynchronizeWithCloud {

	final static String nameSpace = "http://api.webservice.envis.com";  
	final static String endPoint = "http://rmitenvis-env.elasticbeanstalk.com/services/DataProvider?wsdl";  


	public static void syncDB(){

		//String[] args = { set.getId(),set.getName(),set.getLocation().getLongitude()+"",set.getLocation().getLongitude()+"",set.getNotes()};


		String methodName = "getAllSet";
		String soapAction = nameSpace + "/" + methodName;

		Boolean response;

		SoapObject rpc = new SoapObject(nameSpace, methodName);   
		PropertyInfo pi = new PropertyInfo();
		


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
