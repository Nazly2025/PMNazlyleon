# Documentación API con Swagger/OpenAPI

## 📚 Descripción

Este proyecto incluye documentación automática de la API REST usando **SpringDoc OpenAPI** (anteriormente conocido como Swagger).

## 🚀 Cómo acceder a la documentación

### 1. Iniciar el servidor
```bash
./mvnw spring-boot:run
```

### 2. Acceder a Swagger UI
Una vez que el servidor esté corriendo, puedes acceder a la documentación interactiva en:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## 📖 Características de la documentación

### ✅ Endpoints documentados
- `GET /api/products/{id}` - Obtener detalles de un producto

### ✅ Información incluida
- **Descripción de cada endpoint**
- **Parámetros requeridos** con ejemplos
- **Códigos de respuesta** (200, 404, 500)
- **Esquemas de datos** (DTOs)
- **Ejemplos de respuesta**

### ✅ Funcionalidades de Swagger UI
- **Probar endpoints** directamente desde el navegador
- **Ver esquemas** de datos
- **Descargar** la especificación OpenAPI
- **Filtrar** por tags y métodos HTTP

## 🔧 Configuración

### Archivos de configuración
- `OpenApiConfig.java` - Configuración principal de OpenAPI
- `application.properties` - Configuración de SpringDoc
- Anotaciones en controladores y DTOs

### Anotaciones utilizadas
```java
@Tag(name = "Productos", description = "API para gestión de productos")
@Operation(summary = "Obtener producto por ID", description = "...")
@ApiResponses(value = { @ApiResponse(...) })
@Parameter(description = "ID del producto", example = "1")
@Schema(description = "Detalles completos de un producto")
```

## 🎯 Ejemplo de uso

### 1. Abrir Swagger UI
Ve a http://localhost:8080/swagger-ui.html

### 2. Probar un endpoint
1. Expande el endpoint `GET /api/products/{id}`
2. Haz clic en "Try it out"
3. Ingresa un ID (ej: 1)
4. Haz clic en "Execute"
5. Verás la respuesta JSON

### 3. Ver la documentación
- **Schemas**: Ver la estructura de los DTOs
- **Responses**: Ver ejemplos de respuestas
- **Parameters**: Ver descripción de parámetros

## 📝 Agregar nuevos endpoints

Para documentar un nuevo endpoint:

```java
@Operation(
    summary = "Título del endpoint",
    description = "Descripción detallada"
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Éxito"),
    @ApiResponse(responseCode = "404", description = "No encontrado")
})
@GetMapping("/nuevo-endpoint")
public ResponseEntity<MiDTO> miMetodo(
    @Parameter(description = "Descripción del parámetro", example = "ejemplo")
    @PathVariable String parametro
) {
    // Implementación
}
```

## 🔍 Troubleshooting

### Problema: No se ve la documentación
- Verifica que el servidor esté corriendo en puerto 8080
- Revisa los logs por errores de compilación
- Asegúrate de que las dependencias estén descargadas

### Problema: Errores de importación
- Ejecuta `./mvnw clean install` para descargar dependencias
- Verifica que la versión de springdoc-openapi sea compatible

## 📚 Recursos adicionales

- [SpringDoc OpenAPI Documentation](https://springdoc.org/)
- [OpenAPI Specification](https://swagger.io/specification/)
- [Swagger UI](https://swagger.io/tools/swagger-ui/)

---

**¡La documentación está lista! 🎉**
Ahora puedes probar tus endpoints directamente desde el navegador. 