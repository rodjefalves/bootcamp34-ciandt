package com.ciandt.bootcamp.Java.business;

import com.ciandt.bootcamp.Java.business.exception.NotFoundAlertException;
import com.ciandt.bootcamp.Java.business.util.OrderBreweries;
import com.ciandt.bootcamp.Java.business.exception.EmailAlertException;
import com.ciandt.bootcamp.Java.business.exception.base.ProblemKey;
import com.ciandt.bootcamp.Java.domain.Brewerie;
import com.ciandt.bootcamp.Java.domain.Rating;
import com.ciandt.bootcamp.Java.domain.RatingRepository;
import com.ciandt.bootcamp.Java.domain.wrapper.BrewerieWrapper;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 *
 * @Author Augusto Santos
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class BreweryBusiness {

    private final BrewerieFinderService finderService;
    private final RatingRepository ratingRepository;
    private final OrderBreweries orderBreweries;

    public List<BrewerieWrapper> findCities(Optional<String> cidade) {
        final List<Brewerie> breweries = cidade.isEmpty() ? finderService.findAll() : finderService.findByCity(cidade.get());
        validateEmptyResult(breweries);

        List<BrewerieWrapper> brewerieWrapperList = new ArrayList<>();

        for (int i = 0; i < breweries.size(); i++) {

            BrewerieWrapper brewerieWrapper = new BrewerieWrapper(breweries
                    .get(i),
                    ratingRepository
                            .findByBrewerieId(breweries
                                    .get(i)
                                    .getId()));

            brewerieWrapperList.add(brewerieWrapper);

        }

        orderBreweries.orderBreweries(brewerieWrapperList);

        return brewerieWrapperList;
    }

    public BrewerieWrapper findId(String id) {
        try {
            final Brewerie brewerie = finderService.findById(id);
            return new BrewerieWrapper(brewerie, ratingRepository.findByBrewerieId(id));
        } catch (HttpClientErrorException exception) {
            throw new NotFoundAlertException(ProblemKey.BREWERY_NOT_FOUND);
        }
    }


    public void addRating(final String email, final Double stars, final String brewerieId) {
        validateEmailFormat(email);
        validateUniqueRating(email, brewerieId);

        final Rating rating = new Rating();
        rating.setEmail(email);
        rating.setStars(stars);
        rating.setBrewerieId(brewerieId);
        ratingRepository.save(rating);
    }

    private void validateEmailFormat(final String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new EmailAlertException(ProblemKey.INVALID_EMAIL);
        }
    }

    private void validateUniqueRating(final String email, final String brewerieId) {
        if (ratingRepository.existsByEmailAndBrewerieId(email, brewerieId)) {
            throw new EmailAlertException(ProblemKey.USED_EMAIL);
        }
    }

    public void validateEmptyResult(final List<Brewerie> breweries) {
        if (ObjectUtils.isEmpty(breweries)) {
            throw new NotFoundAlertException(ProblemKey.BREWERY_NOT_FOUND);
        }
    }

}
