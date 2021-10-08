package com.mycompany.myapp.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mycompany.myapp.domain.ContentTypeInfo;
import com.test.strapi.masterdata.config.MasterdataConfig;
import com.test.strapi.masterdata.model.StrapiContentType;


@Service 
public class CreateData {
	
	private static final org.slf4j.Logger LOG= LoggerFactory.getLogger(CreateData.class);
	
	@Autowired
	MongoTemplate mongoTemplate;

	
	@SuppressWarnings("unchecked")
	public <T extends StrapiContentType> List<ContentTypeInfo> getData() {
		// TODO Auto-generated method stub
		
		//Listen mit Daten aus  Datenbank befüllen muss noch gemacht werden Ersetzen
		
		
		List<MasterdataConfig> collectionNames = mongoTemplate.findAll(MasterdataConfig.class, "masterdataConfig");
		List<ContentTypeInfo> createdContentTypes = new ArrayList<ContentTypeInfo>();
		
		
		for( MasterdataConfig collection : collectionNames) {
			
			ContentTypeInfo contentTypeInfo = new ContentTypeInfo();
			
			Query q1 = new Query();			
			Query q2 = new Query();			
	    	q1.with(Sort.by(Direction.DESC, "updatedAt"));
	    	q2.addCriteria(Criteria.where("id").exists(false));
	    	List<T> result = null;
	    	try {
	    		result = (List<T>) mongoTemplate.find(q1, collection.getClazz(), collection.getCollectionName());
			} catch (java.lang.IllegalArgumentException e) {
				LOG.error("Class not found, Check Type of Masterdata Config or Update Projekt Models! ");
			}
	    	
	    	
	    	
	    	//Wichtig Konvention für ToString einhalten!
	    	if(result!=null) {
	    		if(!result.isEmpty()) {
		    		contentTypeInfo.setContentTypeName(collection.getContentType());
		    		contentTypeInfo.setEnviroment(collection.getEnvironment());
		    		try {
		    			String lastUpdated=result.get(0).getUpdatedAt().toString();
			    		lastUpdated = lastUpdated.substring(0, 10);
			    		contentTypeInfo.setLastModifiedDate(lastUpdated);	 
		    		} catch (java.lang.NullPointerException e) {
		    			System.out.println("Field LastUpdated could not be updated");
		    		}
		    		catch (java.lang.ClassCastException e){
		    			System.out.println("Cast not possible ");
		    		}
		    		   		
		    		contentTypeInfo.setNumberOfEntries((int)  mongoTemplate.count(q2, collection.getCollectionName()));
		    		Field[] countFields=result.get(0).getClass().getDeclaredFields();
		    		contentTypeInfo.setNumberOfParameters(countFields.length);
		    		
		    	}
	    	}
	    
	    	createdContentTypes.add(contentTypeInfo);
		}
		return createdContentTypes;
	}
	
	

	
}
