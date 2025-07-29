package com.pruebameli.pruebameli_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Necesario para la deserialización de Json
@AllArgsConstructor // Conveniente para crear objetos
public class ProductFeatureDTO {
    private String name;
    private String value;
}