package com.prueba.Prueba.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.prueba.Prueba.repository.Price;

public interface PriceService {
    Price getPriceByParametersAndPriority(final LocalDateTime applicationDate, final Long productId, final Integer brandId);
    List<Price> getAllPrices();
}
