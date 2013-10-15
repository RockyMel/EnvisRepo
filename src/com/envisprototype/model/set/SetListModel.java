package com.envisprototype.model.set;

import java.util.ArrayList;
import java.util.List;



public class SetListModel implements SetListInterface{

	private static SetListInterface singletonInstance;
	List<SetInterface> setList = new ArrayList<SetInterface>();

	//only one model!!
		public static SetListInterface getSingletonInstance() {
			if(singletonInstance==null)
				singletonInstance=new SetListModel();

			return singletonInstance;
		}
	
	@Override
	public void addSet(SetInterface newset) {
		// TODO Auto-generated method stub
		setList.add(newset);
		
		
	}

	@Override
	public void removeSet(SetInterface set) {
		// TODO Auto-generated method stub
		setList.remove(set);
		
	}

	@Override
	public SetInterface findSetById(String id) {
		// TODO Auto-generated method stub
		for(SetInterface set: setList){
			if(set.getId().equals(id))
				return set;						
		}
		return  null;
				
	}

	@Override
	public List<SetInterface> getSetList() {
		// TODO Auto-generated method stub
		List<SetInterface> temp = setList;
		return temp;
	}

	@Override
	public void setSetList(List<SetInterface> theset) {
		// TODO Auto-generated method stub
		this.setList = theset;
	}

	@Override
	public void addAssociateSettoMap(String setID, String mapID, float x,
			float y, float z) {
		// TODO Auto-generated method stub
		for(int i=0;i<this.setList.size();i++)
		{
			if(this.setList.get(i).getId().equals(setID))
				{
				this.setList.get(i).setMapID(mapID);
				this.setList.get(i).setX(x);
				this.setList.get(i).setY(y);
				this.setList.get(i).setZ(z);
				
				}
		}
		
	}

}
