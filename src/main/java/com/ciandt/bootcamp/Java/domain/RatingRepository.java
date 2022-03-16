package com.ciandt.bootcamp.Java.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    List<Rating> findByBrewerieId(String id);
    Rating findByEmailAndBrewerieId(String email,String brewerieId);
    Boolean existsByEmailAndBrewerieId(String email,String brewerieId);
    void deleteByEmailAndBrewerieId(String email,String brewerieId);
}
