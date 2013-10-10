package com.envisprototype.model;

import java.util.ArrayList;
import java.util.List;

public class ParameterConstruct {

	String name;
	List<Integer> parameter1 = new ArrayList<Integer>();
	Number[] number1;

	public Number[] getNumber1() {
		return number1;
	}
	public void setNumber1(Number[] number1) {
		this.number1 = number1;
	}
	public ParameterConstruct(String name, List<Integer> parameter12, Number[] data) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.parameter1 = parameter12;
		this.number1=data;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getParameter1() {
		return parameter1;
	}
	public void setParameter1(List<Integer> parameter1) {
		this.parameter1 = parameter1;
	}

	
}
