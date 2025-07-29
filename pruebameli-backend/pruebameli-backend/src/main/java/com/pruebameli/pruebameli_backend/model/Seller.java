package com.pruebameli.pruebameli_backend.model;

import jakarta.persistence.Column; // Para personalizar el mapeo de columnas (opcional, pero útil)
import jakarta.persistence.Entity; // Indispensable: Marca esta clase como una entidad JPA
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue; // Indispensable: Para claves primarias generadas automáticamente
import jakarta.persistence.GenerationType; // Indispensable: Tipo de estrategia de generación de ID
import jakarta.persistence.Id; // Indispensable: Marca el campo como clave primaria
import jakarta.persistence.Lob; // Para campos de texto potencialmente largos
import jakarta.persistence.OneToMany; // Para la relación inversa con Product (un vendedor puede tener muchos productos)

import lombok.Data; // De Lombok: Genera getters, setters, equals, hashCode y toString
import lombok.ToString;
import lombok.NoArgsConstructor; // De Lombok: Genera constructor sin argumentos
import lombok.AllArgsConstructor; // De Lombok: Genera constructor con todos los argumentos

import java.util.List; // Necesario si usas Listas, como para la relación con productos


@Entity // Esta anotación le dice a JPA que esta clase es una tabla en la base de datos
@Data   // Anotación de Lombok para reducir el código repetitivo (getters, setters, etc.)
@NoArgsConstructor // Constructor sin argumentos, útil para JPA
@AllArgsConstructor // Constructor con todos los argumentos, útil para inicialización
public class Seller {

    @Id // Marca 'id' como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que la base de datos generará este ID automáticamente (autoincremento)
    private Long id;

    @Column(nullable = false) // Indica que el nombre del vendedor no puede ser nulo
    private String name;

    private Double rating; // Calificación promedio del vendedor
    private Integer totalSales; // Cantidad total de ventas
    private Integer yearsActive; // Años activo como vendedor

    private Double qualityRating; // Calificación de calidad del producto
    private Double deliveryRating; // Calificación de cumplimiento en entregas
    private Double serviceRating; // Calificación de servicio al cliente

    @Lob // Se usa para campos de texto largos. Útil si la cadena JSON de insignias puede ser muy extensa.
    @Column(name = "badges_json") // Nombre de la columna en la DB para las insignias
    private String badgesJson; // Almacenará las insignias como un String JSON (ej: "[\"MercadoLíder Platinum\", \"Envío Gratis\"]")

    private String location; // Ubicación del vendedor

    // Relación inversa con Product: Un Seller puede tener MUCHOS Products
    // 'mappedBy' indica que la relación es gestionada por el campo 'seller' en la entidad Product.
    // FetchType.LAZY es generalmente recomendado para colecciones grandes para evitar cargas innecesarias.
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Product> products; // Lista de productos vendidos por este vendedor

    // Nota: Los getters y setters para todos estos campos serán generados automáticamente por Lombok (@Data).
}