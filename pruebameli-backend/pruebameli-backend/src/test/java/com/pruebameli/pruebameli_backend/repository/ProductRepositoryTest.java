package com.pruebameli.pruebameli_backend.repository;

import com.pruebameli.pruebameli_backend.model.Product;
import com.pruebameli.pruebameli_backend.model.Seller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("ProductRepository Tests")
class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    private Seller testSeller;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        // Limpiar base de datos de prueba
        entityManager.clear();

        // Crear y persistir Seller
        testSeller = new Seller();
        testSeller.setName("Tienda Oficial Samsung");
        testSeller.setRating(4.8f);
        testSeller.setTotalSales(15000);
        testSeller.setYearsActive(10);
        testSeller.setLocation("Bogotá, Colombia");
        testSeller.setBadgesJson("[\"MercadoLíder Platinum\", \"Envío Gratis\"]");
        
        testSeller = entityManager.persistAndFlush(testSeller);

        // Crear Product
        testProduct = new Product();
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
    @DisplayName("Should find product by ID when product exists")
    void shouldFindProductByIdWhenProductExists() {
        // Given
        Product savedProduct = entityManager.persistAndFlush(testProduct);

        // When
        Optional<Product> foundProduct = productRepository.findProductById(savedProduct.getId());

        // Then
        assertTrue(foundProduct.isPresent());
        assertEquals(savedProduct.getId(), foundProduct.get().getId());
        assertEquals("Samsung Galaxy A55 5G Dual SIM 256 GB", foundProduct.get().getName());
        assertEquals(439.0, foundProduct.get().getPrice());
        assertEquals("Electrónica", foundProduct.get().getCategory());
        assertEquals("Samsung", foundProduct.get().getBrand());
        assertEquals(15, foundProduct.get().getAvailableStock());
        assertEquals("12 meses de garantía oficial por el vendedor", foundProduct.get().getWarranty());
    }

    @Test
    @DisplayName("Should return empty when product not found")
    void shouldReturnEmptyWhenProductNotFound() {
        // When
        Optional<Product> foundProduct = productRepository.findProductById(999L);

        // Then
        assertFalse(foundProduct.isPresent());
    }

    @Test
    @DisplayName("Should save product successfully")
    void shouldSaveProductSuccessfully() {
        // When
        Product savedProduct = productRepository.save(testProduct);

        // Then
        assertNotNull(savedProduct.getId());
        assertEquals("Samsung Galaxy A55 5G Dual SIM 256 GB", savedProduct.getName());
        assertEquals(439.0, savedProduct.getPrice());
        assertEquals(testSeller.getId(), savedProduct.getSeller().getId());
    }

    @Test
    @DisplayName("Should find product with seller information")
    void shouldFindProductWithSellerInformation() {
        // Given
        Product savedProduct = entityManager.persistAndFlush(testProduct);

        // When
        Optional<Product> foundProduct = productRepository.findProductById(savedProduct.getId());

        // Then
        assertTrue(foundProduct.isPresent());
        assertNotNull(foundProduct.get().getSeller());
        assertEquals("Tienda Oficial Samsung", foundProduct.get().getSeller().getName());
        assertEquals(4.8f, foundProduct.get().getSeller().getRating());
        assertEquals(15000, foundProduct.get().getSeller().getTotalSales());
        assertEquals(10, foundProduct.get().getSeller().getYearsActive());
        assertEquals("Bogotá, Colombia", foundProduct.get().getSeller().getLocation());
    }

    @Test
    @DisplayName("Should handle product with null image URLs")
    void shouldHandleProductWithNullImageUrls() {
        // Given
        testProduct.setImageUrls(null);
        Product savedProduct = entityManager.persistAndFlush(testProduct);

        // When
        Optional<Product> foundProduct = productRepository.findProductById(savedProduct.getId());

        // Then
        assertTrue(foundProduct.isPresent());
        assertNull(foundProduct.get().getImageUrls());
    }

    @Test
    @DisplayName("Should handle product with null features JSON")
    void shouldHandleProductWithNullFeaturesJson() {
        // Given
        testProduct.setFeaturesJson(null);
        Product savedProduct = entityManager.persistAndFlush(testProduct);

        // When
        Optional<Product> foundProduct = productRepository.findProductById(savedProduct.getId());

        // Then
        assertTrue(foundProduct.isPresent());
        assertNull(foundProduct.get().getFeaturesJson());
    }

    @Test
    @DisplayName("Should handle product with empty image URLs")
    void shouldHandleProductWithEmptyImageUrls() {
        // Given
        testProduct.setImageUrls(Arrays.asList());
        Product savedProduct = entityManager.persistAndFlush(testProduct);

        // When
        Optional<Product> foundProduct = productRepository.findProductById(savedProduct.getId());

        // Then
        assertTrue(foundProduct.isPresent());
        assertNotNull(foundProduct.get().getImageUrls());
        assertTrue(foundProduct.get().getImageUrls().isEmpty());
    }
} 