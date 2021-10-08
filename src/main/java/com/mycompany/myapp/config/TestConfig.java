package com.mycompany.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.strapi.masterdata.remote.MasterdataFetchService;
import com.test.strapi.masterdata.remote.MasterdataStoreService;
import com.test.strapi.masterdata.service.MasterdataConfigStoreService;

@Configuration
public class TestConfig {

	@Bean
	public MasterdataStoreService masterDataStoreService() {
		return new MasterdataStoreService();
	}
	
	@Bean
	public MasterdataFetchService masterDataFetcheService() {
		return new MasterdataFetchService();
	}
	
	@Bean
	public MasterdataConfigStoreService masterdataConfigStoreService() {
		return new MasterdataConfigStoreService();
	}
}
