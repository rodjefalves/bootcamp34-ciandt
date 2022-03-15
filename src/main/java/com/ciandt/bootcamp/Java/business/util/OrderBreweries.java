package com.ciandt.bootcamp.Java.business.util;

import com.ciandt.bootcamp.Java.domain.wrapper.BrewerieWrapper;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class OrderBreweries {

    public void orderBreweries(List<BrewerieWrapper> brewerieWrappers) {

        orderByRatingThenName(brewerieWrappers);

    }

    private void orderByRatingThenName(List<BrewerieWrapper> brewerieWrappers) {

        Comparator<BrewerieWrapper> compareByRatingThenName = Comparator
                .comparing(BrewerieWrapper::getPoints).reversed()
                .thenComparing(brewerieWrapper -> brewerieWrapper
                        .getBrewerie()
                        .getName());

        brewerieWrappers.sort(compareByRatingThenName);

    }

}
