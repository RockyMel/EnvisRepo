package com.envisprototype.model.maps;

import java.util.HashMap;

import android.location.Location;

import com.envisprototype.model.processing.Coordinates;

public abstract class AbstractMap implements MapInterface {
	private String id;
	private String name;
	private Location location;
	Coordinates realCoordinates;
	private Float zCoordinate;
	HashMap<String,SetCoordinate> SetCoordinateHashMap;

	public AbstractMap() {

		//this.id=UUID.randomUUID().toString();
		this.location = new Location("0");
		this.name = "default";
		this.realCoordinates = new Coordinates();
		this.zCoordinate = -50f;
	}
	public AbstractMap(String name, Location location) {
		super();
		this.name = name;
		this.location = location;
		this.realCoordinates=new Coordinates();
		this.SetCoordinateHashMap=new HashMap<String,SetCoordinate>();
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
	 * @see com.example.envisproto.Model.Maps.MapInterface#getId()
	 */
	@Override
	public String getId(){
		return this.id;

	}
	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.Maps.MapInterface#setId(java.lang.String)
	 */
	@Override
	public void setId(String id){
		this.id=id;

	}

	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.Maps.MapInterface#setId(java.lang.String)
	 */
	@Override
	public  void addRealCoordinates(Float x,Float y){
		realCoordinates.getCoorX().add(x);
		realCoordinates.getCoorY().add(y);
	}

	public  void removeRealCoordinates(int nodeToRemove){
		realCoordinates.getCoorX().remove(nodeToRemove);
		realCoordinates.getCoorY().remove(nodeToRemove);
	}

	public void shiftNodes(int index){
		for(int i = realCoordinates.getCoorX().size()-1; i > index ; i--){

			realCoordinates.getCoorX().set(i, realCoordinates.getCoorX().get(i-1));
			realCoordinates.getCoorY().set(i, realCoordinates.getCoorY().get(i-1));
		}
	}

	public Coordinates getRealCoordinates() {
		return realCoordinates;
	}

	public void setRealCoordinates(Coordinates realCoordinates) {
		this.realCoordinates = realCoordinates;
	}

	public Float getzCoordinate() {
		return zCoordinate;
	}

	public void setzCoordinate(Float zCoordinate) {
		this.zCoordinate = zCoordinate;
	}

	public void addSetToMap(String SetID,SetCoordinate xyz)
	{
		this.SetCoordinateHashMap.put(SetID, xyz);


	}

	public void removeSetFromMap(String SetID)
	{
		this.SetCoordinateHashMap.remove(SetID);


	}
	public HashMap<String, SetCoordinate> getSetCoordinateHashMap() {
		return SetCoordinateHashMap;
	}
	public void setSetCoordinateHashMap(HashMap<String, SetCoordinate> setCoordinateHashMap) {
		SetCoordinateHashMap = setCoordinateHashMap;
	}
	


	
}
