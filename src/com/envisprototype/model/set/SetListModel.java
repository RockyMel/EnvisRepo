package com.envisprototype.model.set;

import java.util.ArrayList;
import java.util.List;

import com.envisprototype.model.maps.MapInterface;



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
	public List<SetInterface> getSetListByIds(List<String> setIds) {
		// TODO Auto-generated method stub
		List<SetInterface> temp = new ArrayList<SetInterface>();
		for(SetInterface setToReturn: setList){
			for(String setId: setIds){
				if(setToReturn.getId().equals(setId)){
					temp.add(setToReturn);
				}
			}
		}
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

	@Override
	public void ReplicateSetList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editSet(SetInterface set) {
		// TODO Auto-generated method stub
		for(SetInterface setToEdit: setList){
			if(setToEdit.getId().equals(set.getId())){
				setToEdit.setName(set.getName());
				setToEdit.setLocation(set.getLocation());
				setToEdit.setNotes(set.getNotes());
			}
		}
	}

	@Override
	public void addAssociateSensorToMap(String setID, float x, float y, float z) {
		// TODO Auto-generated method stub
		
	}

}
