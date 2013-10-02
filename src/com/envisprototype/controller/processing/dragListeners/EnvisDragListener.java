package com.envisprototype.controller.processing.dragListeners;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public interface EnvisDragListener {
	List _drag_listeners = new ArrayList();
	public void handleEnvisDragEvent(EventObject e);
}
