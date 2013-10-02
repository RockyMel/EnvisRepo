package com.envisprototype.model.set;

import java.util.List;

public interface SetListInterface {

	public abstract void addSet(SetInterface newset);
	
	public abstract void removeSet(SetInterface set);
	
	public abstract SetInterface findSetById(String id);
	
	public abstract List<SetInterface> getSetList();
	
	
	
}
