# 🛒 Proyecto E-commerce - Prueba Técnica MercadoLibre

## 📋 Descripción

Este proyecto es una aplicación web de e-commerce que simula la funcionalidad de MercadoLibre, desarrollada como prueba técnica. Consta de un **backend** en Spring Boot y un **frontend** en React.

## 🏗️ Arquitectura del Proyecto

```
pruebameli-backend/
├── pruebameli-backend/     # Backend Spring Boot
│   ├── src/
│   ├── pom.xml
│   └── README-Swagger.md
└── pruebameli-front/       # Frontend React
    ├── src/
    ├── package.json
    └── vite.config.js
```

## 🛠️ Tecnologías Utilizadas

### Backend (Spring Boot)
- **Java 21** - Lenguaje de programación
- **Spring Boot 3.5.4** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **SQLite** - Base de datos
- **SpringDoc OpenAPI** - Documentación de API (Swagger)
- **Lombok** - Reducción de código boilerplate
- **JUnit 5 + Mockito** - Testing
- **Maven** - Gestión de dependencias

### Frontend (React)
- **React 18.3.1** - Framework de UI
- **Vite 5.4.10** - Build tool y dev server
- **React Router DOM 7.7.1** - Enrutamiento
- **Tailwind CSS 3.4.17** - Framework CSS
- **Radix UI** - Componentes de UI accesibles
- **React Hook Form 7.54.1** - Manejo de formularios
- **Zod 3.24.1** - Validación de esquemas
- **Vitest** - Testing framework
- **TypeScript 5.7.3** - Tipado estático

## 🚀 Guía de Despliegue

### Prerrequisitos

Antes de comenzar, asegúrate de tener instalado:

- **Java 21** (JDK)
- **Node.js 18+** y npm
- **Git**

### Paso 1: Clonar el Repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd pruebameli-backend
```

### Paso 2: Configurar el Backend

#### 2.1 Navegar al directorio del backend
```bash
cd pruebameli-backend
```

#### 2.2 Verificar Java
```bash
java -version
# Debe mostrar Java 21
```

#### 2.3 Compilar y ejecutar el proyecto
```bash
# Usando Maven Wrapper (recomendado)
./mvnw clean install
./mvnw spring-boot:run

# O usando Maven directamente
mvn clean install
mvn spring-boot:run
```

#### 2.4 Verificar que el backend esté funcionando
- El servidor se iniciará en: `http://localhost:8080`
- Documentación Swagger: `http://localhost:8080/swagger-ui.html`
- API Docs: `http://localhost:8080/api-docs`

### Paso 3: Configurar el Frontend

#### 3.1 Navegar al directorio del frontend
```bash
cd ../pruebameli-front
```

#### 3.2 Instalar dependencias
```bash
npm install
```

#### 3.3 Ejecutar en modo desarrollo
```bash
npm run dev
```

#### 3.4 Verificar que el frontend esté funcionando
- La aplicación se abrirá en: `http://localhost:5173`

### Paso 4: Verificar la Integración

1. **Backend funcionando**: `http://localhost:8080`
2. **Frontend funcionando**: `http://localhost:5173`
3. **API documentada**: `http://localhost:8080/swagger-ui.html`

## 📚 Documentación de la API

### Endpoints Disponibles

- `GET /api/products/{id}` - Obtener detalles de un producto
- Documentación completa en: `http://localhost:8080/swagger-ui.html`

### Base de Datos

- **SQLite** - Base de datos embebida
- Archivo: `products.db`
- Se inicializa automáticamente con datos de prueba

## 🧪 Testing

### Backend
```bash
cd pruebameli-backend
./mvnw test
```

### Frontend
```bash
cd pruebameli-front
npm run test
npm run test:coverage
```

## 🏗️ Comandos Útiles

### Backend
```bash
# Compilar
./mvnw clean compile

# Ejecutar tests
./mvnw test

# Ejecutar con perfil específico
./mvnw spring-boot:run -Dspring.profiles.active=dev

# Generar JAR ejecutable
./mvnw clean package
```

### Frontend
```bash
# Instalar dependencias
npm install

# Modo desarrollo
npm run dev

# Build para producción
npm run build

# Preview del build
npm run preview

# Ejecutar tests
npm run test
npm run test:ui
```

## 🔧 Configuración de Desarrollo

### Variables de Entorno

#### Backend
- Puerto por defecto: `8080`
- Base de datos: SQLite embebida
- Configuración en: `application.properties`

#### Frontend
- Puerto por defecto: `5173`
- API URL: `http://localhost:8080`
- Configuración en: `vite.config.js`

## 🐛 Troubleshooting

### Problemas Comunes

#### Backend no inicia
```bash
# Verificar Java
java -version

# Limpiar y reinstalar
./mvnw clean install

# Verificar puerto 8080 disponible
netstat -an | grep 8080
```

#### Frontend no inicia
```bash
# Limpiar node_modules
rm -rf node_modules package-lock.json
npm install

# Verificar puerto 5173 disponible
netstat -an | grep 5173
```

#### Problemas de CORS
- El backend ya incluye configuración CORS
- Si persisten problemas, verificar configuración en `WebConfig.java`

## 📁 Estructura del Proyecto

### Backend
```
src/main/java/com/pruebameli/pruebameli_backend/
├── controller/     # Controladores REST
├── model/         # Entidades JPA
├── repository/    # Repositorios de datos
├── dto/          # Objetos de transferencia
└── conf/         # Configuraciones
```

### Frontend
```
src/
├── components/    # Componentes React
├── assets/       # Recursos estáticos
├── test/         # Configuración de tests
└── App.jsx       # Componente principal
```

## 🚀 Despliegue en Producción

### Backend
```bash
# Generar JAR
./mvnw clean package

# Ejecutar JAR
java -jar target/pruebameli-backend-0.0.1-SNAPSHOT.jar
```

### Frontend
```bash
# Build de producción
npm run build

# Servir archivos estáticos
npm run preview
```

## 📞 Soporte

Para problemas o preguntas:
1. Revisar la documentación de Swagger
2. Verificar logs del backend
3. Revisar consola del navegador para errores del frontend

---

**¡El proyecto está listo para usar! 🎉** 