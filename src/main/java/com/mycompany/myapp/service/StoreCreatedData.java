package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;
import com.mycompany.myapp.domain.ContentTypeInfo;

@Service
public class StoreCreatedData {

	@Autowired
	private CreateData createData;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void storeData() {
		
		List<ContentTypeInfo> contentTypes = createData.getData();
			
		for(ContentTypeInfo contentType : contentTypes) {
			
			if(contentType.getNumberOfEntries()==null&&contentType.getLastModifiedDate()==null) continue;
			Query q = new Query();
			q.addCriteria(Criteria.where("content_type_name").is(contentType.getContentTypeName())
					.and("enviroment").is(contentType.getEnviroment()));
			
			Update updateData = new Update();
			
			updateData.set("number_of_entries", contentType.getNumberOfEntries())
			.set("number_of_parameters", contentType.getNumberOfParameters())
			.set("last_modified_date", contentType.getLastModifiedDate());
			
			UpdateResult result =  mongoTemplate.updateMulti(q, updateData, "content_type_info");
			
			if(result.getMatchedCount()==0) mongoTemplate.save(contentType, "content_type_info");
			
			
			
			
		}
		
		
		
		
	}
	
	
}
