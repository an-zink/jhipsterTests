package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MasterdataConfig;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the MasterdataConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MasterdataConfigRepository extends MongoRepository<MasterdataConfig, String> {

}
