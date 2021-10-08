package com.mycompany.myapp.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.strapi.masterdata.config.MasterdataConfig;
import com.test.strapi.masterdata.service.MasterdataConfigStoreService;

@Controller
@RequestMapping("/config/masterdata")
public class MasterDataConfigController {
	
	@Autowired
	MasterdataConfigStoreService maConfigStoreService;
	
	@PostMapping
	public ResponseEntity<MasterdataConfig> storeMasterdata(@RequestBody MasterdataConfig masterdataConfig) {
		MasterdataConfig created;
		created = maConfigStoreService.storeMasterdataConfig(masterdataConfig);
		return new ResponseEntity<MasterdataConfig>(created,HttpStatus.CREATED);
	}

}
