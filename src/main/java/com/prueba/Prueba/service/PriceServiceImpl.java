package com.prueba.Prueba.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.Prueba.repository.Price;
import com.prueba.Prueba.repository.PricesRepository;

@Service
public class PriceServiceImpl implements PriceService{

    @Autowired
    private PricesRepository pricesRepository;

    public Price getPriceByParametersAndPriority(final LocalDateTime applicationDate, final Long productId, final Integer brandId) {
        final List<Price> results = pricesRepository.findByParameters(applicationDate, productId, brandId);

        return results.stream()
            .max(Comparator.comparing(Price::getPriority))
            .orElseThrow(NoSuchElementException::new);
    }

    public List<Price> getAllPrices() {
        return this.pricesRepository.findAll();
    }
}
