package com.envisprototype.view.processing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.envisprototype.controller.processing.dragListeners.EnvisDragEventClass;
import com.envisprototype.controller.processing.dragListeners.EnvisDragListener;
import com.envisprototype.controller.processing.eventListeners.EnvisButtonListener;
import com.envisprototype.controller.processing.eventListeners.EnvisEventClass;


public abstract class UIElement {
	protected EnvisPApplet epApplet;
	protected String text;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public static float[] color = {123,123,123};
	public static final int R = 0, G = 1, B = 2;
	private List _listeners = new ArrayList();
	private List _drag_listeners = new ArrayList();
	
	public synchronized void addEventListener(EnvisButtonListener listener)  {
		_listeners.add(listener);
		}
	public synchronized void removeEventListener(EnvisButtonListener listener)   {
		_listeners.remove(listener);
		} 
	
	public synchronized void addDragEventListener(EnvisButtonListener listener)  {
		_drag_listeners.add(listener);
		}
	public synchronized void removeDragEventListener(EnvisButtonListener listener)   {
		_drag_listeners.remove(listener);
		} 

		  // call this method whenever you want to notify
		  //the event listeners of the particular event
	public synchronized void fireEvent() {
		EnvisEventClass event = new EnvisEventClass(this);
		Iterator i = _listeners.iterator();
		while(i.hasNext())  {
			((EnvisButtonListener) i.next()).handleEnvisClassEvent(event);
			}
		}
	
	public synchronized void fireDragEvent() {
		EnvisDragEventClass event = new EnvisDragEventClass(this);
		Iterator i = _drag_listeners.iterator();
		while(i.hasNext())  {
			((EnvisDragListener) i.next()).handleEnvisDragEvent(event);
			}
		}
	
	public EnvisPApplet getEpApplet() {
		return epApplet;
	}
	
	
	public void setColor(float r, float g, float b){
		color[R] = r;
		color[G] = g;
		color[B] = b;
	}
	
	public float[] getColor(){
		return color;
	}


	public void setEpApplet(EnvisPApplet epApplet) {
		this.epApplet = epApplet;
	}


	public UIElement(EnvisPApplet epApplet) {
		this.epApplet = epApplet;
		this.text = "";
	}
	
	public UIElement(EnvisPApplet epApplet, String name) {
		this.epApplet = epApplet;
		this.text = name;
	}
	
	public abstract void drawMe();
	public abstract void rotate(float xRotate, float yRotate, float zRotate);
}
