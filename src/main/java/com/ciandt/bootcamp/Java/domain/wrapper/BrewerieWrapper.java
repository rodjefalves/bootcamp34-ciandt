package com.ciandt.bootcamp.Java.domain.wrapper;

import com.ciandt.bootcamp.Java.business.exception.NotFoundAlertException;
import com.ciandt.bootcamp.Java.business.exception.base.ProblemKey;
import com.ciandt.bootcamp.Java.domain.Brewerie;
import com.ciandt.bootcamp.Java.domain.Rating;
import lombok.ToString;

import java.util.List;

@ToString
public class BrewerieWrapper {

    private final Brewerie brewerie;
    private final List<Rating> ratings;
    private Double points;

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

    public Double getPoints() {

        Double sumPoints = 0.0d;

        for (Rating rating : ratings) {

            sumPoints += rating.getStars();

        }

        points = sumPoints/ratings.size();

        if (points.isNaN())
            points = Double.valueOf("0");

        return points;

    }

}
