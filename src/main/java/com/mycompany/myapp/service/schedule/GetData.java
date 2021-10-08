package com.mycompany.myapp.service.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mycompany.myapp.event.ContentTypeCreatedFinishedEvent;
import com.mycompany.myapp.event.TriggerContentTypeRefreshEvent;
import com.mycompany.myapp.service.StoreCreatedData;

import io.vavr.control.Try;

@Service
@EnableScheduling
public class GetData {

	private static final Logger LOG = LoggerFactory.getLogger(GetData.class);
	
	@Autowired
	private StoreCreatedData createdData;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Scheduled(fixedDelay = 10000*2, initialDelay = 5000)
	@EventListener(classes = { TriggerContentTypeRefreshEvent.class})
	public void init() {
		Try
		  .of(this::load)
	        .onSuccess(response -> LOG.info("ContentType created successfully."))
	        .onSuccess(response -> applicationEventPublisher.publishEvent(new ContentTypeCreatedFinishedEvent(this)))
	        .onFailure(response -> LOG.info("Fail creation of ContentTypes", response.getCause()));
		
	}
	
	private boolean load () {
		createdData.storeData();
		return true;
	}
	
}
