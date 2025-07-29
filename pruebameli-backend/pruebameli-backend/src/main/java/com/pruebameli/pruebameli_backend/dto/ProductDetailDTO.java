// DTO para exponer los detalles de un producto al frontend
package com.pruebameli.pruebameli_backend.dto;

import com.pruebameli.pruebameli_backend.model.Product;
import com.pruebameli.pruebameli_backend.model.Review;
import com.pruebameli.pruebameli_backend.model.Seller;
import com.pruebameli.pruebameli_backend.dto.ProductFeatureDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "Detalles completos de un producto")
public class ProductDetailDTO {
    // --- Campos expuestos al frontend ---
    @Schema(description = "ID único del producto", example = "1")
    private Long id;
    
    @Schema(description = "Nombre del producto", example = "Samsung Galaxy A55 5G Dual SIM 256 GB")
    private String name;
    
    @Schema(description = "Descripción detallada del producto")
    private String description;
    
    @Schema(description = "Precio del producto", example = "1299999.0")
    private Double price;
    
    @Schema(description = "Lista de URLs de imágenes del producto")
    private List<String> imageUrls;
    
    @Schema(description = "Categoría del producto", example = "Electrónica")
    private String category;
    
    @Schema(description = "Marca del producto", example = "Samsung")
    private String brand;
    
    @Schema(description = "Stock disponible", example = "25")
    private Integer availableStock;
    
    @Schema(description = "Información de garantía", example = "12 meses de garantía oficial")
    private String warranty;
    
    @Schema(description = "Lista de características del producto")
    private List<ProductFeatureDTO> features;
    
    @Schema(description = "Rating promedio del producto", example = "4.8")
    private Double rating;
    
    @Schema(description = "Cantidad de reseñas", example = "1247")
    private Integer reviewsCount;
    
    @Schema(description = "Información del vendedor")
    private SellerDTO sellerInfo;
    
    @Schema(description = "Lista de reseñas del producto")
    private List<ReviewDTO> reviews;

    // Constructor que mapea desde la entidad Product
    public ProductDetailDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        // Maneja el caso de imageUrls nulo
        this.imageUrls = (product.getImageUrls() != null) ? product.getImageUrls() : Collections.emptyList();
        this.category = product.getCategory();
        this.brand = product.getBrand();
        this.availableStock = product.getAvailableStock();
        this.warranty = product.getWarranty();
        // Parseo de featuresJson a lista de DTOs
        if (product.getFeaturesJson() != null && !product.getFeaturesJson().isEmpty()) {
            try {
                this.features = new ObjectMapper().readValue(product.getFeaturesJson(), new TypeReference<List<ProductFeatureDTO>>() {});
            } catch (Exception e) {
                // SUGERENCIA: Usa un logger en vez de System.err
                System.err.println("Error al parsear featuresJson del producto " + product.getId() + ": " + e.getMessage());
                this.features = Collections.emptyList();
            }
        } else {
            this.features = Collections.emptyList();
        }
        // Calcula rating y cantidad de reviews
        if (product.getReviews() != null && !product.getReviews().isEmpty()) {
            this.reviewsCount = product.getReviews().size();
            this.rating = product.getReviews().stream()
                               .mapToInt(Review::getRating)
                               .average()
                               .orElse(0.0);
        } else {
            this.reviewsCount = 0;
            this.rating = 0.0;
        }
        // Mapea el vendedor a SellerDTO
        if (product.getSeller() != null) {
            this.sellerInfo = new SellerDTO(product.getSeller());
        } else {
            this.sellerInfo = null;
        }
        // Mapea las reviews a ReviewDTO
        if (product.getReviews() != null) {
            this.reviews = product.getReviews().stream()
                                 .map(ReviewDTO::new)
                                 .collect(Collectors.toList());
        } else {
            this.reviews = Collections.emptyList();
        }
    }
    // --- Getters para serialización JSON ---
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Double getPrice() { return price; }
    public List<String> getImageUrls() { return imageUrls; }
    public String getCategory() { return category; }
    public String getBrand() { return brand; }
    public Integer getAvailableStock() { return availableStock; }
    public String getWarranty() { return warranty; }
    public List<ProductFeatureDTO> getFeatures() { return features; }
    public Double getRating() { return rating; }
    public Integer getReviewsCount() { return reviewsCount; }
    public SellerDTO getSellerInfo() { return sellerInfo; }
    public List<ReviewDTO> getReviews() { return reviews; }
}