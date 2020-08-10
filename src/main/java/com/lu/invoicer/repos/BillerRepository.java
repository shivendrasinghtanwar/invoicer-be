package com.lu.invoicer.repos;

import com.lu.invoicer.models.Biller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface BillerRepository extends MongoRepository<Biller, String> {

  Biller findByEmail(String email);
}

