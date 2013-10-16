package com.envisprototype.model.set;

import java.util.List;

public interface SetListInterface {

	public abstract void addSet(SetInterface newset);
	
	public abstract void removeSet(SetInterface set);
	
	public abstract SetInterface findSetById(String id);
	
	public abstract List<SetInterface> getSetList();

	public abstract void setSetList(List<SetInterface> theset);

	public abstract void addAssociateSettoMap(String setID, String mapID,
			float x, float y, float z);
	
	
	
}
