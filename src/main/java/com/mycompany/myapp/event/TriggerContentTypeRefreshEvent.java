package com.mycompany.myapp.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class TriggerContentTypeRefreshEvent extends ApplicationContextEvent{


	private static final long serialVersionUID = 1L;

	public TriggerContentTypeRefreshEvent(ApplicationContext source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
