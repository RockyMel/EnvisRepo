package com.envisprototype.model.sensor;

import java.util.ArrayList;
import java.util.List;

import com.envisprototype.model.set.SetListInterface;
import com.envisprototype.model.set.SetListModel;




public class SensorListModel implements SensorListInterface  {

	private static SensorListInterface singletonInstance;
	public static final String EVENT_ID_EXTRA="eventidextra";
	List<SensorInterface> sensorList = new ArrayList<SensorInterface>();

	
	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.Model#addSensor(com.example.envisproto.Model.SensorInterface)
	 */
	@Override
	public void addSensor(SensorInterface sensor){
		sensorList.add(sensor);
	}
	
	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.Model#removeSensor(com.example.envisproto.Model.SensorInterface)
	 */
	@Override
	public void removeSensor(SensorInterface sensor){
		sensorList.remove(sensor);
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.Model#findSensorById(java.lang.String)
	 */
	@Override
	public SensorInterface findSensorById(String Id){
		
		for(SensorInterface sensor: sensorList){
			if(sensor.getId().equals(Id))
				return sensor;						
		}
		return  null;
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.Model#getSensorList()
	 */
	@Override
	public List<SensorInterface> getSensorList(){

		//can still modify individual events
		return sensorList;
	}
	
	//only one model!!
	public static SensorListInterface getSingletonInstance() {
		if(singletonInstance==null)
			singletonInstance=new SensorListModel();

		return singletonInstance;
	}
	@Override
	public boolean isSensorListEmpty()
	{
		return sensorList.isEmpty();

	}
	
	@Override
	public List<SensorInterface> getSensorListBySetID(String ID)
	{
		List<SensorInterface> temp = new ArrayList();
		
		for(SensorInterface sensor: sensorList){
			if(sensor.getSetid().equals(ID))
				temp.add(sensor);
		}
		
		
		return temp;
		

	}

	@Override
	public List getSensorListByType(int type) {
		// TODO Auto-generated method stub
	//	1=Temperature | 2=Light | 3=Sound | 4=Motion | 5=Humidity | 6=Air_Quality | 7=Water_Level | 8=Others
		String typeString =null;
		if(type==1)
			 typeString = "Air Quality";
		if(type==2)
			 typeString = "Humidity";
		if(type==3)
			 typeString = "Light";
		if(type==4)
			 typeString = "Motion";
		if(type==5)
			 typeString = "Temperature";
		if(type==6)
			 typeString = "Water Level";
		if(type==6)
			 typeString = "Others";
		
		
		List<String> temp = new ArrayList();
		
		for(SensorInterface sensor: sensorList){
			if(sensor.getType().equals(typeString))
				if(sensor.getSetid()!=null){
					String Setname = SetListModel.getSingletonInstance().findSetById(sensor.getSetid()).getName();
					temp.add(Setname+"/"+sensor.getName());
				}
				else
					temp.add(sensor.getName()+"--Inactive");
					
		}
		
		
		return temp;
		
	}
	
	
	
}
