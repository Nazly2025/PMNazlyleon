package com.pruebameli.pruebameli_backend.dto;

import com.pruebameli.pruebameli_backend.model.Seller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data // De Lombok
public class SellerDTO {
    private String name;
    private Double rating;
    private Integer totalSales;
    private Integer yearsActive;
    private Double qualityRating;
    private Double deliveryRating;
    private Double serviceRating;
    private List<String> badges; 
    private String location;

    public SellerDTO(Seller seller) {
        this.name = seller.getName();
        this.rating = seller.getRating();
        this.totalSales = seller.getTotalSales();
        this.yearsActive = seller.getYearsActive();
        this.qualityRating = seller.getQualityRating();
        this.deliveryRating = seller.getDeliveryRating();
        this.serviceRating = seller.getServiceRating();
        this.location = seller.getLocation();
        if (seller.getBadgesJson() != null && !seller.getBadgesJson().isEmpty()) {
            try {
                this.badges = new ObjectMapper().readValue(seller.getBadgesJson(), new TypeReference<List<String>>() {});
            } catch (Exception e) {
                this.badges = Collections.emptyList();
            }
        } else {
            this.badges = Collections.emptyList();
        }
    }
}