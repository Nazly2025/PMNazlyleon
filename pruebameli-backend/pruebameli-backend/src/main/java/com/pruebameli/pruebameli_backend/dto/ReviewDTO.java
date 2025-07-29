package com.pruebameli.pruebameli_backend.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.pruebameli.pruebameli_backend.model.Review;
import lombok.Data;

@Data // De Lombok
public class ReviewDTO {
    private Long id;
    private String userName;
    private Integer rating;
    private String comment;
    private String date; // Se formateará de LocalDate a String
    private Integer helpful;
    private Boolean verified;

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.userName = review.getUserName();
        this.rating = review.getRating();
        this.comment = review.getComment();
        // Formatear la fecha para que sea compatible con el frontend
        this.date = review.getReviewDate(); // La fecha ya viene como String en formato "YYYY-MM-DD"
        this.helpful = review.getHelpfulCount();
        this.verified = review.getVerifiedPurchase();
    }
}