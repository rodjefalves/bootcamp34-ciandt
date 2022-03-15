package com.ciandt.bootcamp.Java.domain.wrapper;

import com.ciandt.bootcamp.Java.domain.entity.Brewerie;
import com.ciandt.bootcamp.Java.domain.entity.Rating;
import lombok.ToString;

import java.util.List;

@ToString
public class BrewerieWrapper {

    private final Brewerie brewerie;
    private final List<Rating> ratings;
    private Integer points;

    public BrewerieWrapper(Brewerie brewerie,
                           List<Rating> ratings) {
        this.brewerie = brewerie;
        this.ratings = ratings;
        this.points = getPoints();
    }

    public Brewerie getBrewerie() {
        return brewerie;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public Integer getPoints() {

        Integer sumPoints = 0;

        for (Rating rating : ratings) {

            sumPoints += rating.getStars();

        }

        return sumPoints/ratings.size();

    }

}
