package com.envisprototype.model.processing;

public class XbeeTagPair {
	String tagId;
	Integer xBeeId;
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public Integer getxBeeId() {
		return xBeeId;
	}
	public void setxBeeId(Integer xBeeId) {
		this.xBeeId = xBeeId;
	}
	
	public XbeeTagPair(String tagId, Integer xBeeId){
		this.tagId = tagId;
		this.xBeeId = xBeeId;
	}

}
