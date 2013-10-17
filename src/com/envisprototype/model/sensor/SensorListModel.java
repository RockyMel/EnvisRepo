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
	public List getSensorIDListByType(int type) {
	

		List<String> temp = new ArrayList<String>();

		for(SensorInterface sensor: sensorList){
			if(sensor.getType()==type)
				if(sensor.getSetid()!=null){
					temp.add(sensor.getId());
				}
				else
					temp.add(sensor.getId()+"--Inactive");

		}


		return temp;

	}

	@Override
	public void setSensorList(List<SensorInterface> thesensor) {
		// TODO Auto-generated method stub
		this.sensorList= thesensor;
	}

	@Override
	public void addAssociateSensortoSet(String sensorID, String setID) {
		// TODO Auto-generated method stub
		for(int i=0;i<sensorList.size();i++){
			if(sensorList.get(i).getId().equals(sensorID)){
				sensorList.get(i).setSetid(setID);
			}
		}
	}

	@Override
	public void ReplicateSensorList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editSensor(SensorInterface sensor) {
		// TODO Auto-generated method stub
		
	}



}
