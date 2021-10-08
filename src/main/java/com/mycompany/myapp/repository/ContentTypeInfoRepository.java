package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ContentTypeInfo;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the ContentTypeInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContentTypeInfoRepository extends MongoRepository<ContentTypeInfo, String> {

}
