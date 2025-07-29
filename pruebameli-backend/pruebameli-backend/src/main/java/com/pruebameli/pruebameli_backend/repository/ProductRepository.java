package com.pruebameli.pruebameli_backend.repository;

import com.pruebameli.pruebameli_backend.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; 

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Encuentra un Producto por su ID, cargando ansiosamente (eagerly)
     * sus relaciones de Seller y Reviews utilizando el EntityGraph "product-with-details".
     *
     * @param id El ID del producto a buscar.
     * @return Un Optional que contiene el Producto si se encuentra, con Seller y Reviews cargados.
     */
    @EntityGraph(value = "product-with-details", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Product> findProductById(Long id);
}