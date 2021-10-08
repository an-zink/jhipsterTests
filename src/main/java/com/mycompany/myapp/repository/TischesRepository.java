package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Tisches;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Tisches entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TischesRepository extends MongoRepository<Tisches, String> {

}
