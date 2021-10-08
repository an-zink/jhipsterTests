package com.mycompany.myapp.service.schedule;



import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mycompany.myapp.domain.Auto;
import com.mycompany.myapp.domain.Haus;
import com.mycompany.myapp.domain.Market;
import com.mycompany.myapp.domain.Tisches;
import com.test.strapi.masterdata.MasterdataImportFinishedEvent;
import com.test.strapi.masterdata.TriggerMaserdataRefreshEvent;
import com.test.strapi.masterdata.config.MasterdataConfig;
import com.test.strapi.masterdata.remote.MasterdataStoreService;

import io.vavr.control.Try;






@Service
@Configuration
@EnableScheduling
public class MasterdataConfiguration {

	private static final org.slf4j.Logger LOG= LoggerFactory.getLogger(MasterdataConfiguration.class);
	
	@Autowired
	private MasterdataStoreService masterdataStoreService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Scheduled(fixedDelay = 10000*2, initialDelay = 4000)
	@EventListener(classes = { TriggerMaserdataRefreshEvent.class})
	public void init() {
		Try
        .of(this::load)
        .onSuccess(response -> LOG.info("Masterdata from central server loaded successfully."))
        .onSuccess(response -> applicationEventPublisher.publishEvent(new MasterdataImportFinishedEvent(this)))
        .onFailure(response -> LOG.info("Masterdata from central server could not be loaded.", response.getCause()));
	}
	
	
	private boolean load() {
		
		List<MasterdataConfig> allMasterDataConfig = mongoTemplate.findAll(MasterdataConfig.class,"masterdataConfig");

		for(MasterdataConfig x : allMasterDataConfig) {
			if (x.getContentType().equals("market")) {
				masterdataStoreService.storeData(x, new ParameterizedTypeReference<List<Market>>() {}); 
			}
			if (x.getContentType().equals("auto")) {
				masterdataStoreService.storeData(x, new ParameterizedTypeReference<List<Auto>>() {}); 
			}
			if (x.getContentType().equals("haus")) {
				masterdataStoreService.storeData(x, new ParameterizedTypeReference<List<Haus>>() {}); 
			}
			if (x.getContentType().equals("tisches")) {
				masterdataStoreService.storeData(x, new ParameterizedTypeReference<List<Tisches>>() {}); 
			}
		}
		return true;
		
	}

}
