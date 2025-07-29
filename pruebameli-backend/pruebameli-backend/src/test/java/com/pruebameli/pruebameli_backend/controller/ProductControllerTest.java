package com.pruebameli.pruebameli_backend.controller;

import com.pruebameli.pruebameli_backend.dto.ProductDetailDTO;
import com.pruebameli.pruebameli_backend.model.Product;
import com.pruebameli.pruebameli_backend.model.Seller;
import com.pruebameli.pruebameli_backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
@DisplayName("ProductController Integration Tests")
class ProductControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ProductRepository productRepository;

    private MockMvc mockMvc;
    private Product testProduct;
    private Seller testSeller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        // Configurar datos de prueba
        testSeller = new Seller();
        testSeller.setId(1L);
        testSeller.setName("Tienda Oficial Samsung");
        testSeller.setRating(4.8f);
        testSeller.setTotalSales(15000);
        testSeller.setYearsActive(10);
        testSeller.setLocation("Bogotá, Colombia");
        testSeller.setBadgesJson("[\"MercadoLíder Platinum\", \"Envío Gratis\"]");

        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Samsung Galaxy A55 5G Dual SIM 256 GB");
        testProduct.setDescription("Con su potente procesador y 8 GB de RAM...");
        testProduct.setPrice(439.0);
        testProduct.setCategory("Electrónica");
        testProduct.setBrand("Samsung");
        testProduct.setAvailableStock(15);
        testProduct.setWarranty("12 meses de garantía oficial por el vendedor");
        testProduct.setFeaturesJson("[{\"name\":\"Tamaño de la pantalla\",\"value\":\"6.6 \\\"\"},{\"name\":\"Memoria interna\",\"value\":\"256 GB\"}]");
        testProduct.setSeller(testSeller);
        testProduct.setImageUrls(Arrays.asList(
            "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=800&h=600&fit=crop",
            "https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=800&h=600&fit=crop"
        ));
    }

    @Test
    @DisplayName("Should return product when valid ID is provided")
    void shouldReturnProductWhenValidIdProvided() throws Exception {
        // Given
        when(productRepository.findProductById(1L)).thenReturn(Optional.of(testProduct));

        // When & Then
        mockMvc.perform(get("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Samsung Galaxy A55 5G Dual SIM 256 GB"))
                .andExpect(jsonPath("$.price").value(439.0))
                .andExpect(jsonPath("$.category").value("Electrónica"))
                .andExpect(jsonPath("$.brand").value("Samsung"))
                .andExpect(jsonPath("$.availableStock").value(15))
                .andExpect(jsonPath("$.warranty").value("12 meses de garantía oficial por el vendedor"))
                .andExpect(jsonPath("$.imageUrls").isArray())
                .andExpect(jsonPath("$.imageUrls.length()").value(2))
                .andExpect(jsonPath("$.sellerInfo").exists())
                .andExpect(jsonPath("$.sellerInfo.id").value(1))
                .andExpect(jsonPath("$.sellerInfo.name").value("Tienda Oficial Samsung"))
                .andExpect(jsonPath("$.features").isArray())
                .andExpect(jsonPath("$.features.length()").value(2));
    }

    @Test
    @DisplayName("Should return 404 when product not found")
    void shouldReturn404WhenProductNotFound() throws Exception {
        // Given
        when(productRepository.findProductById(999L)).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/api/products/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should handle invalid ID format")
    void shouldHandleInvalidIdFormat() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/products/invalid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return product with empty reviews when no reviews exist")
    void shouldReturnProductWithEmptyReviewsWhenNoReviewsExist() throws Exception {
        // Given
        testProduct.setReviews(null);
        when(productRepository.findProductById(1L)).thenReturn(Optional.of(testProduct));

        // When & Then
        mockMvc.perform(get("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviews").isArray())
                .andExpect(jsonPath("$.reviews.length()").value(0))
                .andExpect(jsonPath("$.rating").value(0.0))
                .andExpect(jsonPath("$.reviewsCount").value(0));
    }

    @Test
    @DisplayName("Should return product with empty image URLs when no images exist")
    void shouldReturnProductWithEmptyImageUrlsWhenNoImagesExist() throws Exception {
        // Given
        testProduct.setImageUrls(null);
        when(productRepository.findProductById(1L)).thenReturn(Optional.of(testProduct));

        // When & Then
        mockMvc.perform(get("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imageUrls").isArray())
                .andExpect(jsonPath("$.imageUrls.length()").value(0));
    }

    @Test
    @DisplayName("Should handle repository exception gracefully")
    void shouldHandleRepositoryExceptionGracefully() throws Exception {
        // Given
        when(productRepository.findProductById(anyLong())).thenThrow(new RuntimeException("Database error"));

        // When & Then
        mockMvc.perform(get("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
} 