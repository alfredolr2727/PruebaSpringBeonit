package com.prueba.Prueba.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.Prueba.model.PriceResponseDto;
import com.prueba.Prueba.repository.Price;
import com.prueba.Prueba.service.PriceService;

@RestController
@RequestMapping("/api")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/getPriceByPriority")
    public ResponseEntity<PriceResponseDto> getPriceByPriority(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss") LocalDateTime applicationDate,
        @RequestParam Long productId,
        @RequestParam Integer brandId) {
        try {
            Price price = priceService.getPriceByParametersAndPriority(applicationDate, productId, brandId);

            return new ResponseEntity<>(this.convertToPriceResponseDto(price), HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            // Si no encuentra resultados devolvemos Status NO_CONTENT (204)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            // Si ocurre cualquier error no controlado devolvemos INTERNAL_SERVER_ERROR (500)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Aquí con mas tiempo se usaría un mapper en condiciones para separar bien las capas de la aplicacion
    private PriceResponseDto convertToPriceResponseDto(final Price price) {
        final PriceResponseDto priceResponseDto = new PriceResponseDto();
        priceResponseDto.setProductId(price.getProductId());
        priceResponseDto.setBrandId(price.getBrandId());
        priceResponseDto.setPriceList(price.getPriceList());
        priceResponseDto.setStartDate(price.getStartDate());
        priceResponseDto.setEndDate(price.getEndDate());

        // Obtiene el precio final multiplicando el precio por la tarifa a aplicar
        final BigDecimal finalPrice = price.getPrice().multiply(BigDecimal.valueOf(price.getPriceList()));
        priceResponseDto.setFinalPrice(finalPrice);

        return priceResponseDto;
    }

}
