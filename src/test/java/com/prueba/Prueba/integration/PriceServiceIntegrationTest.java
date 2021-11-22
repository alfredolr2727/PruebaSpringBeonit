package com.prueba.Prueba.integration;

import com.prueba.Prueba.repository.Price;
import com.prueba.Prueba.service.PriceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
* Si se tratase de un caso real sería mas meticuloso con el nombre, cantidad y tipo de test que haríamos aquí
* */

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class PriceServiceIntegrationTest {
    @Autowired
    private PriceService priceService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");

    private static final long PRODUCT_ID = 35455L;

    private static final int BRAND_ID = 1;

    @Test
    public void whenGetAllPrices_thenReturnAllPrices() {
        // when
        List<Price> pricesFound = this.priceService.getAllPrices();

        // then
        Assertions.assertEquals(pricesFound.size(), 4);
    }

    @Test
    public void test1() {
        //given
        final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 10.00.00", formatter);

        // when
        Price result = this.priceService.getPriceByParametersAndPriority(applicationDate, PRODUCT_ID, BRAND_ID);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), Integer.valueOf(1));
    }

    @Test
    public void test2() {
        //given
        final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 16.00.00", formatter);

        // when
        Price result = this.priceService.getPriceByParametersAndPriority(applicationDate, PRODUCT_ID, BRAND_ID);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), Integer.valueOf(2));
    }

    @Test
    public void test3() {
        //given
        final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 21.00.00", formatter);

        // when
        Price result = this.priceService.getPriceByParametersAndPriority(applicationDate, PRODUCT_ID, BRAND_ID);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), Integer.valueOf(1));
    }

    @Test
    public void test4() {
        //given
        final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15 10.00.00", formatter);

        // when
        Price result = this.priceService.getPriceByParametersAndPriority(applicationDate, PRODUCT_ID, BRAND_ID);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), Integer.valueOf(3));
    }

    @Test
    public void test5() {
        //given
        final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-16 21.00.00", formatter);

        // when
        Price result = this.priceService.getPriceByParametersAndPriority(applicationDate, PRODUCT_ID, BRAND_ID);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), Integer.valueOf(4));
    }

}
