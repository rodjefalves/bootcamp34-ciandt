package com.ciandt.bootcamp.Java.business;

import com.ciandt.bootcamp.Java.business.exception.EmailAlertException;
import com.ciandt.bootcamp.Java.business.exception.base.ProblemKey;
import com.ciandt.bootcamp.Java.domain.Brewerie;
import com.ciandt.bootcamp.Java.domain.Rating;
import com.ciandt.bootcamp.Java.domain.RatingRepository;
import com.ciandt.bootcamp.Java.domain.wrapper.BrewerieWrapper;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

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

    public List<BrewerieWrapper> findCities(Optional<String> cidade) {
        final List<Brewerie> breweries = cidade.isEmpty() ? finderService.findAll() : finderService.findByCity(cidade.get());
        // Converter Brewerie para BrewerieWrapper e retornar
        return null;
    }

    public BrewerieWrapper findId(String id) {
        final Brewerie brewerie = finderService.findById(id);
        // Converter Brewerie para BrewerieWrapper e retornar
        return null;
    }

    public void addRating(final String email, final Double stars, final String brewerieId) {
        validateEmailFormat(email);
        validadeUniqueRating(email, brewerieId);

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

    private void validadeUniqueRating(final String email, final String brewerieId) {
        if (ratingRepository.existsByEmailAndBrewerieId(email, brewerieId)) {
            throw new EmailAlertException(ProblemKey.USED_EMAIL);
        }
    }
}
