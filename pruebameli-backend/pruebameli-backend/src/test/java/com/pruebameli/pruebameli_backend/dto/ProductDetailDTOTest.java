package com.pruebameli.pruebameli_backend.dto;

import com.pruebameli.pruebameli_backend.model.Product;
import com.pruebameli.pruebameli_backend.model.Seller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProductDetailDTO Tests")
class ProductDetailDTOTest {

    private Product product;
    private Seller seller;

    @BeforeEach
    void setUp() {
        // Configurar Seller
        seller = new Seller();
        seller.setId(1L);
        seller.setName("Tienda Oficial Samsung");
        seller.setRating(4.8f);
        seller.setTotalSales(15000);
        seller.setYearsActive(10);
        seller.setLocation("Bogotá, Colombia");
        seller.setBadgesJson("[\"MercadoLíder Platinum\", \"Envío Gratis\"]");

        // Configurar Product
        product = new Product();
        product.setId(1L);
        product.setName("Samsung Galaxy A55 5G Dual SIM 256 GB");
        product.setDescription("Con su potente procesador y 8 GB de RAM...");
        product.setPrice(439.0);
        product.setCategory("Electrónica");
        product.setBrand("Samsung");
        product.setAvailableStock(15);
        product.setWarranty("12 meses de garantía oficial por el vendedor");
        product.setFeaturesJson("[{\"name\":\"Tamaño de la pantalla\",\"value\":\"6.6 \\\"\"},{\"name\":\"Memoria interna\",\"value\":\"256 GB\"}]");
        product.setSeller(seller);
        product.setImageUrls(Arrays.asList(
            "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=800&h=600&fit=crop",
            "https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=800&h=600&fit=crop"
        ));
    }

    @Test
    @DisplayName("Should create ProductDetailDTO with correct basic data")
    void shouldCreateProductDetailDTOWithCorrectBasicData() {
        // When
        ProductDetailDTO dto = new ProductDetailDTO(product);

        // Then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Samsung Galaxy A55 5G Dual SIM 256 GB", dto.getName());
        assertEquals("Con su potente procesador y 8 GB de RAM...", dto.getDescription());
        assertEquals(439.0, dto.getPrice());
        assertEquals("Electrónica", dto.getCategory());
        assertEquals("Samsung", dto.getBrand());
        assertEquals(15, dto.getAvailableStock());
        assertEquals("12 meses de garantía oficial por el vendedor", dto.getWarranty());
    }

    @Test
    @DisplayName("Should create ProductDetailDTO with correct image URLs")
    void shouldCreateProductDetailDTOWithCorrectImageUrls() {
        // When
        ProductDetailDTO dto = new ProductDetailDTO(product);

        // Then
        assertNotNull(dto.getImageUrls());
        assertEquals(2, dto.getImageUrls().size());
        assertTrue(dto.getImageUrls().contains("https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=800&h=600&fit=crop"));
        assertTrue(dto.getImageUrls().contains("https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=800&h=600&fit=crop"));
    }

    @Test
    @DisplayName("Should create ProductDetailDTO with correct seller information")
    void shouldCreateProductDetailDTOWithCorrectSellerInfo() {
        // When
        ProductDetailDTO dto = new ProductDetailDTO(product);

        // Then
        assertNotNull(dto.getSellerInfo());
        assertEquals(1L, dto.getSellerInfo().getId());
        assertEquals("Tienda Oficial Samsung", dto.getSellerInfo().getName());
        assertEquals(4.8f, dto.getSellerInfo().getRating());
        assertEquals(15000, dto.getSellerInfo().getTotalSales());
        assertEquals(10, dto.getSellerInfo().getYearsActive());
        assertEquals("Bogotá, Colombia", dto.getSellerInfo().getLocation());
    }

    @Test
    @DisplayName("Should parse features JSON correctly")
    void shouldParseFeaturesJsonCorrectly() {
        // When
        ProductDetailDTO dto = new ProductDetailDTO(product);

        // Then
        assertNotNull(dto.getFeatures());
        assertEquals(2, dto.getFeatures().size());
        
        // Verificar primera característica
        assertEquals("Tamaño de la pantalla", dto.getFeatures().get(0).getName());
        assertEquals("6.6 \"", dto.getFeatures().get(0).getValue());
        
        // Verificar segunda característica
        assertEquals("Memoria interna", dto.getFeatures().get(1).getName());
        assertEquals("256 GB", dto.getFeatures().get(1).getValue());
    }

    @Test
    @DisplayName("Should handle null features JSON gracefully")
    void shouldHandleNullFeaturesJsonGracefully() {
        // Given
        product.setFeaturesJson(null);

        // When
        ProductDetailDTO dto = new ProductDetailDTO(product);

        // Then
        assertNotNull(dto.getFeatures());
        assertTrue(dto.getFeatures().isEmpty());
    }

    @Test
    @DisplayName("Should handle empty features JSON gracefully")
    void shouldHandleEmptyFeaturesJsonGracefully() {
        // Given
        product.setFeaturesJson("[]");

        // When
        ProductDetailDTO dto = new ProductDetailDTO(product);

        // Then
        assertNotNull(dto.getFeatures());
        assertTrue(dto.getFeatures().isEmpty());
    }

    @Test
    @DisplayName("Should calculate rating and reviews count correctly")
    void shouldCalculateRatingAndReviewsCountCorrectly() {
        // Given - Product without reviews
        product.setReviews(null);

        // When
        ProductDetailDTO dto = new ProductDetailDTO(product);

        // Then
        assertEquals(0.0, dto.getRating());
        assertEquals(0, dto.getReviewsCount());
    }

    @Test
    @DisplayName("Should handle null image URLs gracefully")
    void shouldHandleNullImageUrlsGracefully() {
        // Given
        product.setImageUrls(null);

        // When
        ProductDetailDTO dto = new ProductDetailDTO(product);

        // Then
        assertNotNull(dto.getImageUrls());
        assertTrue(dto.getImageUrls().isEmpty());
    }
} 