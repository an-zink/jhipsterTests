package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Haus;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Haus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HausRepository extends MongoRepository<Haus, String> {

}
