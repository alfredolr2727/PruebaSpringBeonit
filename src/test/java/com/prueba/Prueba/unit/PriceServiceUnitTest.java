package com.prueba.Prueba.unit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import com.prueba.Prueba.repository.Price;
import com.prueba.Prueba.repository.PricesRepository;
import com.prueba.Prueba.service.PriceServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceUnitTest {

    @Mock
    private PricesRepository pricesRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");

    private static LocalDateTime startDate;

    private static LocalDateTime endDate;

    @BeforeAll
    static void setup() {
        startDate = LocalDateTime.parse("2020-06-14 00.00.00", formatter);
        endDate = LocalDateTime.parse("2020-12-31 23.59.59", formatter);
    }

    @Test
    public void findByParametersWithOneResultTest(){
        // Given
        final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 10.00.00", formatter);
        final Price price = new Price(1, startDate, endDate, 1, 35455L,
            1, BigDecimal.valueOf(35.50), "EUR");

        price.setId(1);

        when(this.pricesRepository.findByParameters(any(LocalDateTime.class), anyLong(), anyInt()))
            .thenReturn(Collections.singletonList(price));

        // When
        final Price result = this.priceService.getPriceByParametersAndPriority(applicationDate, 35455L, 1);


        // Then
        assertNotNull(result);
        assertEquals(result.getId(), 1);
    }

    @Test
    public void findByParametersWithManyResultsTest(){
        // Given
        final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 10.00.00", formatter);
        final Price price1 = new Price(1, startDate, endDate, 1, 35455L,
            1, BigDecimal.valueOf(35.50), "EUR");

        price1.setId(1);

        final Price price2 = new Price(1, startDate, endDate, 1, 35455L,
            2, BigDecimal.valueOf(35.50), "EUR");

        price2.setId(2);

        when(this.pricesRepository.findByParameters(any(LocalDateTime.class), anyLong(), anyInt()))
            .thenReturn(Arrays.asList(price1, price2));

        // When
        final Price result = this.priceService.getPriceByParametersAndPriority(applicationDate, 35455L, 1);


        // Then
        assertNotNull(result);
        assertEquals(result.getId(), 2);
    }

    @Test
    public void findByParametersWithoutResultsTest(){
        // Given
        final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 10.00.00", formatter);

        when(this.pricesRepository.findByParameters(any(LocalDateTime.class), anyLong(), anyInt()))
            .thenReturn(Collections.emptyList());

        // When - Then
        assertThrows(NoSuchElementException.class, () -> this.priceService
            .getPriceByParametersAndPriority(applicationDate, 35455L, 1));

    }

}
