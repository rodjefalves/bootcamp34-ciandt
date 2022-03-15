package com.ciandt.bootcamp.Java.business;

import com.ciandt.bootcamp.Java.domain.Brewerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Service responsible for connecting and retrieving data from Open Brewery DB.
 * @Author Augusto Santos
 * @Version 1.0
 */
@Service
@PropertySource("classpath:external-api.properties")
public class BrewerieFinderService {

    private final RestTemplate restTemplate;
    private final String openBrewerieBaseUrl;
    private final String findParameterCity;

    @Autowired
    public BrewerieFinderService(final RestTemplate restTemplate,
                                 @Value("${open.brewery.api.base-url}") final String openBrewerieBaseUrl,
                                 @Value("${open.brewery.api.find-parameter.city}") final String findParameterCity) {
        this.restTemplate = restTemplate;
        this.openBrewerieBaseUrl = openBrewerieBaseUrl;
        this.findParameterCity = findParameterCity;
    }

    public List<Brewerie> findByCity(final String city) {
        final UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https").host(openBrewerieBaseUrl).query(findParameterCity.concat("={keyword}")).buildAndExpand(city);
        final ResponseEntity<Brewerie[]> response = restTemplate.getForEntity(uri.toUri(), Brewerie[].class);
        final Brewerie[] responseBody = response.getBody();
        return ObjectUtils.isEmpty(responseBody) ? Collections.emptyList() : Arrays.asList(responseBody);
    }

    public Brewerie findById(final String id) {
        final UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https").host(openBrewerieBaseUrl).path("/{id}").buildAndExpand(id);
        final ResponseEntity<Brewerie> response = restTemplate.getForEntity(uri.toUri(), Brewerie.class);
        return response.getBody();
    }
}
