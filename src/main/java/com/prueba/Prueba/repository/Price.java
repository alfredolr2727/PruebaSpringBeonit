package com.prueba.Prueba.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "PRICES")
public class Price {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "brandid")
    private Integer brandId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss")
    @Column(name = "startdate")
    private LocalDateTime startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss")
    @Column(name = "enddate")
    private LocalDateTime endDate;

    @Column(name = "pricelist")
    private Integer priceList;

    @Column(name = "productid")
    private Long productId;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency")
    private String currency;

    public Price() {}

    public Price(@NonNull Integer brandId, @NonNull LocalDateTime startDate, @NonNull LocalDateTime endDate,
        @NonNull Integer priceList, @NonNull Long productId, @NonNull Integer priority, @NonNull BigDecimal price,
        @NonNull String currency) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(@NonNull Integer brandId) {
        this.brandId = brandId;
    }

    @NonNull
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(@NonNull LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @NonNull
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(@NonNull LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @NonNull
    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(@NonNull Integer priceList) {
        this.priceList = priceList;
    }

    @NonNull
    public Long getProductId() {
        return productId;
    }

    public void setProductId(@NonNull Long productId) {
        this.productId = productId;
    }

    @NonNull
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(@NonNull Integer priority) {
        this.priority = priority;
    }

    @NonNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NonNull BigDecimal price) {
        this.price = price;
    }

    @NonNull
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(@NonNull String currency) {
        this.currency = currency;
    }
}
