// Modelo de entidad Product para JPA/Hibernate
// Representa un producto en la base de datos
package com.pruebameli.pruebameli_backend.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // Marca esta clase como una entidad JPA
@Table(name = "product") // Nombre de la tabla en BD
@NamedEntityGraph(
    name = "product-with-details",
    attributeNodes = {
        @NamedAttributeNode("seller"), // Carga el vendedor asociado
        @NamedAttributeNode("reviews") // Carga las reseñas asociadas
        // La lista de imágenes (@ElementCollection) se carga automáticamente
    }
)
public class Product {

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private Long id;

    private String name;
    private String description;
    private Double price;

    // Lista de URLs de imágenes asociadas al producto
    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    private String category;
    private String brand;
    private Integer availableStock;
    private String warranty;

    // Características serializadas como JSON (mejorable: usar entidad embebida o conversor JPA)
    @Column(name = "features_json", columnDefinition = "TEXT")
    private String featuresJson;

    // Relación muchos-a-uno con Seller (vendedor)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    // Relación uno-a-muchos con Review (reseñas)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    // --- Constructores ---
    public Product() {} // Constructor por defecto requerido por JPA

    // Constructor útil para inicialización manual
    public Product(String name, String description, Double price, String category, String brand,
                   Integer availableStock, String warranty, String featuresJson, Seller seller) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.availableStock = availableStock;
        this.warranty = warranty;
        this.featuresJson = featuresJson;
        this.seller = seller;
        this.imageUrls = new ArrayList<>();
    }

    // --- Getters y Setters ---
    // SUGERENCIA: Si usas Lombok, puedes reemplazar estos métodos con @Getter/@Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public List<String> getImageUrls() { return imageUrls; }
    public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public Integer getAvailableStock() { return availableStock; }
    public void setAvailableStock(Integer availableStock) { this.availableStock = availableStock; }
    public String getWarranty() { return warranty; }
    public void setWarranty(String warranty) { this.warranty = warranty; }
    public String getFeaturesJson() { return featuresJson; }
    public void setFeaturesJson(String featuresJson) { this.featuresJson = featuresJson; }
    public Seller getSeller() { return seller; }
    public void setSeller(Seller seller) { this.seller = seller; }
    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    // Métodos de ayuda para mantener la relación bidireccional con Review
    public void addReview(Review review) {
        reviews.add(review);
        review.setProduct(this);
    }
    public void removeReview(Review review) {
        reviews.remove(review);
        review.setProduct(null);
    }
}