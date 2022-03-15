package com.ciandt.bootcamp.Java.business;

import com.ciandt.bootcamp.Java.domain.Brewerie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@SpringBootTest
public class BrewerieFinderServiceIntegrationTest {

    @Autowired
    private BrewerieFinderService finderService;

    @Test
    public void findBreweriesByCity() {
        final String VALID_CITY = "san diego";
        final List<Brewerie> breweries = finderService.findByCity(VALID_CITY);
        Assertions.assertFalse(breweries.isEmpty());
    }

    @Test
    public void findBrewerieById() {
        final String VALID_ID = "10-barrel-brewing-co-bend-2";
        final Brewerie brewerie = finderService.findById(VALID_ID);
        Assertions.assertNotNull(brewerie);
    }

    @Test
    public void findBreweriesByInvalidCity() {
        final String INVALID_CITY = "AAA";
        final List<Brewerie> breweries = finderService.findByCity(INVALID_CITY);
        Assertions.assertTrue(breweries.isEmpty());
    }

    @Test
    public void findBrewerieByInvalidId() {
        final String INVALID_ID = "-";
        final HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class,
                () -> finderService.findById(INVALID_ID));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
