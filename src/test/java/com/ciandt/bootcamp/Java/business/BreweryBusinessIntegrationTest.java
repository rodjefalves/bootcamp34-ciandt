package com.ciandt.bootcamp.Java.business;

import com.ciandt.bootcamp.Java.business.exception.EmailAlertException;
import com.ciandt.bootcamp.Java.business.exception.base.ProblemKey;
import com.ciandt.bootcamp.Java.domain.Rating;
import com.ciandt.bootcamp.Java.domain.RatingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class BreweryBusinessIntegrationTest {

    @Autowired
    private BreweryBusiness breweryBusiness;

    @Autowired
    private RatingRepository ratingRepository;

    private final String validEmail = "time@time.com";
    private final String invalidEmail = "a.a";
    private final String brewerieId = "valid-id";
    private final Double stars = 5.0d;
    private final String id = "some_id";

    @Test
    public void addValidRating() {

        breweryBusiness.addRating(validEmail, stars, brewerieId);

        final Rating savedRating = ratingRepository.findByEmailAndBrewerieId(validEmail, brewerieId);
        Assertions.assertNotNull(savedRating);
        Assertions.assertEquals(validEmail, savedRating.getEmail());
        Assertions.assertEquals(brewerieId, savedRating.getBrewerieId());
        Assertions.assertEquals(stars, savedRating.getStars());

        // Remove Item
        ratingRepository.deleteByEmailAndBrewerieId(validEmail, brewerieId);
        Assertions.assertFalse(ratingRepository.existsByEmailAndBrewerieId(validEmail, brewerieId));
    }

    @Test
    public void addRatingWithAlreadyUsedEmail() {
        final Rating rating = new Rating();
        rating.setBrewerieId(brewerieId);
        rating.setEmail(validEmail);
        rating.setStars(stars);
        rating.setId(id);
        ratingRepository.save(rating);

        final EmailAlertException exception = Assertions.assertThrows(EmailAlertException.class,
                () -> breweryBusiness.addRating(rating.getEmail(), rating.getStars(), rating.getBrewerieId()));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getStatus().getStatusCode());
        Assertions.assertEquals(ProblemKey.USED_EMAIL, exception.getProblemKey());

        // Remove Item
        ratingRepository.deleteByEmailAndBrewerieId(rating.getEmail(), rating.getBrewerieId());
        Assertions.assertFalse(ratingRepository.existsByEmailAndBrewerieId(rating.getEmail(), rating.getBrewerieId()));
    }

    @Test
    public void addRatingWithInvalidEmail() {
        final EmailAlertException exception = Assertions.assertThrows(EmailAlertException.class,
                () -> breweryBusiness.addRating(invalidEmail, stars, brewerieId));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getStatus().getStatusCode());
        Assertions.assertEquals(ProblemKey.INVALID_EMAIL, exception.getProblemKey());
    }
}
