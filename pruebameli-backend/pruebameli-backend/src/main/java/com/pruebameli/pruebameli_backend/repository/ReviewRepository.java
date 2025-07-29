package com.pruebameli.pruebameli_backend.repository;

import com.pruebameli.pruebameli_backend.model.Review; // Importa tu entidad Review
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Review.
 * Proporciona métodos CRUD y de búsqueda para la tabla 'review' en la base de datos.
 */
@Repository // Opcional, pero buena práctica
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // JpaRepository<[Tu Entidad], [Tipo de la Clave Primaria de Tu Entidad]>

    // Métodos de búsqueda comunes para reseñas, por ejemplo:
    // List<Review> findByProductId(Long productId); // Para obtener todas las reseñas de un producto
    // List<Review> findByProductIdOrderByReviewDateDesc(Long productId); // Ordenadas por fecha descendente
    // List<Review> findByRatingGreaterThanEqual(Integer rating); // Reseñas con un rating mínimo
}