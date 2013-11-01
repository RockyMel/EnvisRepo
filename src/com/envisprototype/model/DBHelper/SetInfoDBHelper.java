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
	final static String endPoint = "http://115.146.92.166/EnvisAWS/services/DataProvider?wsdl";  


	public static void addSet(SetInterface set){



		String methodName = "addSets";
		String soapAction = nameSpace + "/" + methodName;


		SoapObject rpc = new SoapObject(nameSpace, methodName);   

		rpc.addProperty("setInfos", set.getId());
		rpc.addProperty("setInfos", set.getName());
		rpc.addProperty("setInfos", set.getLocation().getLongitude()+"");
		rpc.addProperty("setInfos", set.getLocation().getLatitude()+"");
		rpc.addProperty("setInfos", set.getNotes());
		// what about xyz?



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


	public static String generateSetID() {
		// TODO Auto-generated method stub
		String methodName = "generateSetID";
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

		Log.i("SETDBHELPER", "done");
		return resultsRequestSOAP.toString();
	}





}
