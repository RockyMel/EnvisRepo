package com.envisprototype.controller.processing.eventListeners;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import com.envisprototype.view.processing.AbstractEnvisButton;
// from here http://castever.wordpress.com/2008/07/31/how-to-create-your-own-events-in-java/

public interface EnvisButtonListener {
	List _listeners = new ArrayList();
	public void handleEnvisClassEvent(EventObject e);
}
