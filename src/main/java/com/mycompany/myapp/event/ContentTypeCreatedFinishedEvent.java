package com.mycompany.myapp.event;

import org.springframework.context.ApplicationEvent;

public class ContentTypeCreatedFinishedEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public ContentTypeCreatedFinishedEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
