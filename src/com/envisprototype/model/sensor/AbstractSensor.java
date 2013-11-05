package com.envisprototype.model.sensor;

import com.envisprototype.model.processing.SetCoordinates;

import android.location.Location;

public abstract class AbstractSensor implements SensorInterface {

	private String setid;
	
	private String id;
	private String name;
	private int type;
	private String brand;
	private String notes;
	private String state;
	private double minValue;
	private double maxValue;
	private Location location;
	private boolean ifDefaultCoors = true;
	private boolean iftoPlot; 
	private float x,y,z;
	
	public final static int SENSORTYPE_AIR_QUALITY = 1;
	public final static int SENSORTYPE_HUMIDITY = 2;
	public final static int SENSORTYPE_LIGHT = 3;
	public final static int SENSORTYPE_MOTION = 4;
	public final static int SENSORTYPE_TEMPERATURE = 5;
	public final static int SENSORTYPE_WATER_LEVEL = 6;
	public final static int SENSORTYPE_OTHERS = 7;
	
	public AbstractSensor() {

		//this.id=UUID.randomUUID().toString();

	}
	
	public AbstractSensor(String name,int type,String brand,String notes,
			String state, Location location, double minValue, double maxValue) {
		super();
		this.name = name;
		this.type=type;
		this.location = location;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.sensor.SensorInterface#getType()
	 */
	@Override
	public int getType() {
		return type;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.sensor.SensorInterface#setType(java.lang.String)
	 */
	@Override
	public void setType(int type) {
		this.type = type;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.sensor.SensorInterface#getNotes()
	 */
	@Override
	public String getNotes() {
		return notes;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.sensor.SensorInterface#setNotes(java.lang.String)
	 */
	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.sensor.SensorInterface#getState()
	 */
	@Override
	public String getState() {
		return state;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.sensor.SensorInterface#setState(java.lang.String)
	 */
	@Override
	public void setState(String state) {
		this.state = state;
	}
	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.SensorInterface#getName()
	 */

	@Override
	public String getName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.SensorInterface#setName(java.lang.String)
	 */

	@Override
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.SensorInterface#getLocation()
	 */
	
	@Override
	public Location getLocation() {
		return location;
	}
	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.SensorInterface#setLocation(android.location.Location)
	 */

	@Override
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/* (non-Javadoc)
	 * @see com.envisprototype.model.sensor.SensorInterface#getId()
	 */
	@Override
	public String getId(){
		return this.id;
		
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.sensor.SensorInterface#setId(java.lang.String)
	 */
	@Override
	public void setId(String id){
		this.id=id;
		
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.sensor.SensorInterface#setId(java.lang.String)
	 */
	@Override
	public String getSetid() {
		return setid;
	}
	/* (non-Javadoc)
	 * @see com.envisprototype.model.sensor.SensorInterface#setId(java.lang.String)
	 */
	@Override
	public void setSetid(String setid) {
		this.setid = setid;
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
	public boolean isIfDefaultCoors() {
		return ifDefaultCoors;
	}
	
	@Override
	public void setIfDefaultCoors(boolean ifDefaultCoors) {
		this.ifDefaultCoors = ifDefaultCoors;
	}
	@Override
	public boolean isIftoPlot() {
		return iftoPlot;
	}
	@Override
	public void setIftoPlot(boolean iftoPlot) {
		this.iftoPlot = iftoPlot;
	}
	
	@Override
	public double getMinValue(){
		return minValue;
	}
	@Override
	public void setMinValue(double minValue){
		this.minValue = minValue;
	}
	@Override
	public double getMaxValue(){
		return maxValue;
	}
	@Override
	public void setMaxValue(double maxValue){
		this.maxValue = maxValue;
	}
	
	
}
