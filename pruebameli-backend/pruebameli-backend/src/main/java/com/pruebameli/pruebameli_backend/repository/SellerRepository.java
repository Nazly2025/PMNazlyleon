package com.pruebameli.pruebameli_backend.repository;

import com.pruebameli.pruebameli_backend.model.Seller; // Importa tu entidad Seller
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Seller.
 * Proporciona métodos CRUD y de búsqueda para la tabla 'seller' en la base de datos.
 */
@Repository // Opcional, pero buena práctica para indicar que es un componente de repositorio
public interface SellerRepository extends JpaRepository<Seller, Long> {
    // JpaRepository<[Tu Entidad], [Tipo de la Clave Primaria de Tu Entidad]>

    // Aquí puedes añadir métodos de búsqueda personalizados si los necesitas, por ejemplo:
    // Optional<Seller> findByName(String name);
    // List<Seller> findByRatingGreaterThan(Double rating);
}