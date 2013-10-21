package com.envisprototype.model.set;

import java.util.List;

public interface SetListInterface {

	public abstract void addSet(SetInterface newset);
	
	public abstract void removeSet(SetInterface set);
	
	public abstract SetInterface findSetById(String id);
	
	public abstract List<SetInterface> getSetList();

	public abstract void setSetList(List<SetInterface> theset);
	public abstract void ReplicateSetList();

	public abstract void addAssociateSettoMap(String setID, String mapID,
			float x, float y, float z);

	public abstract void editSet(SetInterface set);

	public List<SetInterface> getSetListByIds(List<String> setIds);

	public abstract void addAssociateSensorToMap(String setID, float x,
			float y, float z);
	
	
	
}
