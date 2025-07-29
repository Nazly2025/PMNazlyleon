// Controlador REST para productos
package com.pruebameli.pruebameli_backend.controller;

import com.pruebameli.pruebameli_backend.model.Product;
import com.pruebameli.pruebameli_backend.repository.ProductRepository;
import com.pruebameli.pruebameli_backend.dto.ProductDetailDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5175"})
@Tag(name = "Productos", description = "API para gestión de productos")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Obtiene los detalles completos de un producto por su ID
     * Incluye información del vendedor, reseñas, imágenes y características
     */
    @Operation(
        summary = "Obtener producto por ID",
        description = "Retorna los detalles completos de un producto incluyendo vendedor, reseñas, imágenes y características"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Producto encontrado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ProductDetailDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Producto no encontrado"
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Error interno del servidor"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> getProductById(
        @Parameter(description = "ID del producto a buscar", example = "1")
        @PathVariable Long id
    ) {
        Optional<Product> product = productRepository.findProductById(id);
        return product.map(p -> ResponseEntity.ok(new ProductDetailDTO(p)))
                .orElse(ResponseEntity.notFound().build());
    }
}