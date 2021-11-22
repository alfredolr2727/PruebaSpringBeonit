package com.prueba.Prueba.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// No he querido usar la librería Lombok para generar Getters, Setters, etc.. por si da problemas
public class PriceResponseDto {

    private Long productId;

    private Integer brandId;

    private Integer priceList;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private BigDecimal finalPrice;

    public PriceResponseDto() {}

    public PriceResponseDto(Long productId, Integer brandId, Integer priceList, LocalDateTime startDate,
        LocalDateTime endDate, BigDecimal finalPrice) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.finalPrice = finalPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }
}
