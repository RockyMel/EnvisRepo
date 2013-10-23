package com.envisprototype.model.set;

import android.location.Location;

public abstract class AbstractSet implements SetInterface {

	private String id;
	private String name;
	private String notes;
	private Location location;
	
	private boolean iftoPlot; 
	private float x, y, z;
	private String MapID = null;
	//private List<String> sensorid;
	
	public AbstractSet(){
		//sensorid = new ArrayList<String>();
	}
//	
//	@Override
//	public List<String> getSensors() {
//		return sensors;
//	}
//	
//	@Override
//	public void setSensors(List<SensorInterface> sensors) {
//		this.sensors = sensors;
//	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.set.SetInterface#getId()
	 */
	@Override
	public String getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.set.SetInterface#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.set.SetInterface#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.set.SetInterface#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.set.SetInterface#getNotes()
	 */
	@Override
	public String getNotes() {
		return notes;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.set.SetInterface#setNotes(java.lang.String)
	 */
	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.set.SetInterface#getLocation()
	 */
	@Override
	public Location getLocation() {
		return location;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.set.SetInterface#setLocation(android.location.Location)
	 */
	@Override
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public boolean isIftoPlot() {
		return iftoPlot;
	}
	public void setIftoPlot(boolean iftoPlot) {
		this.iftoPlot = iftoPlot;
	}
	
	@Override
	public float getX() {
		return x;
	}
	
	@Override
	public void setX(float x) {
		this.x = x;
	}
	
	@Override
	public float getY() {
		return y;
	}
	
	@Override
	public void setY(float y) {
		this.y = y;
	}
	
	@Override
	public float getZ() {
		return z;
	}
	
	@Override
	public void setZ(float z) {
		this.z = z;
	}
	
	@Override
	public String getMapID() {
		return MapID;
	}
	@Override
	public void setMapID(String mapID) {
		MapID = mapID;
	}
	
	
	
//	public  SensorInterface findSensorById(String id){
//		for(int i=0;i<sensors.size();i++)
//		{
//			if(sensors.get(i).getId().equals(id))
//				{
//				return sensors.get(i);
//				}
//		}
//		
//		
//		return null;
//	}
//	
//	public SensorInterface getSensor(String id)
//	{
//		for(int i=0;i<sensors.size();i++)
//		{
//			if(sensors.get(i).getId().equals(id))
//				{
//				return sensors.get(i);
//				}
//			
//		}
//		return null;
//	}
//	
//	public void addSensor(SensorInterface sensor){
//		
//		sensors.add(sensor);
//		
//	}
//	
//	public void removeSensor(SensorInterface sensor){
//		sensors.remove(sensor);
//	}
//
//	public  boolean searchSensorById(String id){
//		for(int i=0;i<sensors.size();i++)
//		{
//			if(sensors.get(i).getId().equals(id))
//				{
//				return true;
//				}
//		}
//		
//		return false;
//	}
//	
//	
	
	
	
}
