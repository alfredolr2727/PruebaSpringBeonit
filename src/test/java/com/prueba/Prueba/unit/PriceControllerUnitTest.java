package com.prueba.Prueba.unit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prueba.Prueba.controller.PriceController;
import com.prueba.Prueba.model.PriceResponseDto;
import com.prueba.Prueba.repository.Price;
import com.prueba.Prueba.service.PriceService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceControllerUnitTest {

    private static PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");

    private static Price price;

    @BeforeAll
    static void setup() {
        priceService = mock(PriceService.class);
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-14 00.00.00", formatter);
        final LocalDateTime endDate = LocalDateTime.parse("2020-12-31 23.59.59", formatter);
        price = new Price(1, startDate, endDate, 1, 35455L,
            1, BigDecimal.valueOf(35.50), "EUR");

        when(priceService.getPriceByParametersAndPriority(any(LocalDateTime.class), anyLong(), anyInt()))
            .thenReturn(price);
    }

    @Test
    public void getPriceByPrioritySuccessTest(){
        // Given
        final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 10.00.00", formatter);

        // When
        final ResponseEntity<PriceResponseDto> response = this.priceController
            .getPriceByPriority(applicationDate, 35455L, 1);

        final BigDecimal finalPrice = this.price.getPrice().multiply(BigDecimal.valueOf(this.price.getPriceList()));

        // Then
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(response.getBody()).getFinalPrice(), finalPrice);
    }

    @Test
    public void getPriceByPriorityNoContentTest(){
        // Given
        final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 10.00.00", formatter);

        when(this.priceService.getPriceByParametersAndPriority(any(LocalDateTime.class), anyLong(), anyInt()))
            .thenThrow(NoSuchElementException.class);

        // When
        final ResponseEntity<PriceResponseDto> response = this.priceController
            .getPriceByPriority(applicationDate, 35455L, 1);

        // Then
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

}
