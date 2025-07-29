-- Opcional: Eliminar tablas si existen para empezar limpio.
-- Descomenta estas líneas si quieres que la base de datos se resetee cada vez que inicies Spring Boot.
-- Esto es útil en desarrollo, pero NO en producción.
-- Precaución: El orden de eliminación es importante debido a las claves foráneas.
-- DROP TABLE IF EXISTS review;
-- DROP TABLE IF EXISTS product_images; -- ¡Asegúrate de que esta tabla se elimine primero si la descomentas!
-- DROP TABLE IF EXISTS product;
-- DROP TABLE IF EXISTS seller;

-- INSERTAR SELLERS (VENDEDORES)
INSERT INTO seller (id, name, rating, total_sales, years_active, quality_rating, delivery_rating, service_rating, badges_json, location) VALUES
(1, 'Tienda Oficial Samsung', 4.8, 15000, 10, 4.7, 4.9, 4.8, '["MercadoLíder Platinum", "Envío Gratis"]', 'Bogotá, Colombia'),
(2, 'Apple Store Oficial', 4.9, 20000, 15, 4.9, 4.9, 5.0, '["Tienda Oficial", "MercadoLíder Gold"]', 'Medellín, Colombia'),
(3, 'Motorola Colombia', 4.5, 8000, 7, 4.4, 4.6, 4.5, '["MercadoLíder"]', 'Cali, Colombia');

-- INSERTAR PRODUCTOS
-- ¡¡IMPORTANTE!! Hemos ELIMINADO la columna 'image_url' de esta sentencia INSERT.
INSERT INTO product (id, name, description, price, category, brand, available_stock, warranty, features_json, seller_id) VALUES
(1, 'Samsung Galaxy A55 5G Dual SIM 256 GB', 'Con su potente procesador y 8 GB de RAM, tu computadora logrará un alto rendimiento con una alta velocidad de transmisión de contenido y ejecutará varias aplicaciones al mismo tiempo, sin demoras. Capacidad de almacenamiento ilimitada. Olvídate de borrar. Con su memoria interna de 256 GB puedes descargar todos los archivos y aplicaciones que quieras, guardar fotos y almacenar tus películas, series y videos favoritos para reproducirlos cuando quieras.', 439.00, 'Electrónica', 'Samsung', 15, '12 meses de garantía oficial por el vendedor', '[{"name": "Tamaño de la pantalla", "value": "6.6 \""}, {"name": "Memoria interna", "value": "256 GB"}, {"name": "Cámara trasera principal", "value": "50 Mpx"}, {"name": "Memoria RAM", "value": "8 GB"}, {"name": "Con NFC", "value": "Sí"}, {"name": "Cámara frontal principal", "value": "32 Mpx"}]', 1),
(2, 'iPhone 15 Pro Max 256GB', 'El último iPhone con Chip A17 Bionic y Dynamic Island. Rendimiento extremo y cámara profesional.', 1300.00, 'Electrónica', 'Apple', 8, '1 año de garantía limitada de Apple', '[{"name": "Tamaño de la pantalla", "value": "6.7 \""}, {"name": "Memoria interna", "value": "256 GB"}, {"name": "Cámara trasera principal", "value": "48 Mpx"}, {"name": "Memoria RAM", "value": "8 GB"}, {"name": "Con NFC", "value": "Sí"}, {"name": "Cámara frontal principal", "value": "12 Mpx"}]', 2),
(3, 'Samsung Galaxy M55 5G', 'Excelente opción de gama media alta.', 421.00, 'Electrónica', 'Samsung', 25, '12 meses de garantía', '[{"name": "Tamaño de la pantalla", "value": "6.7 \""}, {"name": "Memoria interna", "value": "128 GB"}, {"name": "Cámara trasera principal", "value": "50 Mpx"}]', 1),
(4, 'Motorola Edge 50 Pro 5G', 'Rendimiento y diseño en un solo equipo.', 419.00, 'Electrónica', 'Motorola', 20, '12 meses de garantía', '[{"name": "Tamaño de la pantalla", "value": "6.7 \""}, {"name": "Memoria interna", "value": "256 GB"}, {"name": "Cámara trasera principal", "value": "50 Mpx"}]', 3),
(5, 'Smart TV Samsung 4K 55 Pulgadas', 'Experimenta la mejor calidad de imagen con este Smart TV 4K.', 750.00, 'Electrónica', 'Samsung', 10, '2 años de garantía', '[]', 1),
(6, 'Auriculares Sony WH-1000XM5', 'Cancelación de ruido líder en la industria.', 349.00, 'Electrónica', 'Sony', 30, '1 año de garantía', '[]', 2);

-- INSERTAR IMÁGENES ADICIONALES
-- Estas inserciones son las que poblalán la tabla product_images
INSERT INTO product_images (product_id, image_url) VALUES
-- Samsung Galaxy A55 5G (Producto 1) - Imágenes reales de celulares Samsung
(1, 'https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=800&h=600&fit=crop'),
(1, 'https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=800&h=600&fit=crop'),
(1, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=800&h=600&fit=crop'),
(1, 'https://images.unsplash.com/photo-1567581935884-3349723552ca?w=800&h=600&fit=crop'),
-- iPhone 15 Pro Max (Producto 2) - Imágenes reales de iPhone
(2, 'https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=800&h=600&fit=crop'),
(2, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=800&h=600&fit=crop'),
(2, 'https://images.unsplash.com/photo-1567581935884-3349723552ca?w=800&h=600&fit=crop'),
(2, 'https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=800&h=600&fit=crop&crop=face'),
-- Samsung Galaxy M55 5G (Producto 3) - Imágenes reales de celulares
(3, 'https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=800&h=600&fit=crop'),
(3, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=800&h=600&fit=crop'),
(3, 'https://images.unsplash.com/photo-1567581935884-3349723552ca?w=800&h=600&fit=crop'),
(3, 'https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=800&h=600&fit=crop&crop=entropy'),
-- Motorola Edge 50 Pro 5G (Producto 4) - Imágenes reales de celulares
(4, 'https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=800&h=600&fit=crop'),
(4, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=800&h=600&fit=crop'),
(4, 'https://images.unsplash.com/photo-1567581935884-3349723552ca?w=800&h=600&fit=crop'),
(4, 'https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=800&h=600&fit=crop&crop=top'),
-- Smart TV Samsung (Producto 5) - Mantenemos placeholder para TV
(5, 'https://via.placeholder.com/400/2196F3/FFFFFF?text=Smart+TV+Samsung+4K'),
-- Auriculares Sony (Producto 6) - Mantenemos placeholder para auriculares
(6, 'https://via.placeholder.com/400/F44336/FFFFFF?text=Sony+WH-1000XM5');


-- INSERTAR RESEÑAS PARA TODOS LOS PRODUCTOS
INSERT INTO review (product_id, user_name, rating, comment, review_date, helpful_count, verified_purchase) VALUES
-- Reseñas para Samsung Galaxy A55 5G (Producto 1)
(1, 'Ana G.', 5, '¡Producto excelente! Muy satisfecha con la compra. La cámara es increíble y la batería dura todo el día.', '2024-07-20', 10, TRUE),
(1, 'Carlos R.', 4, 'Buen producto, cumple con lo prometido. El rendimiento es muy bueno para el precio.', '2024-06-15', 5, TRUE),
(1, 'María L.', 5, 'Excelente relación calidad-precio. Lo recomiendo totalmente.', '2024-07-10', 8, TRUE),
(1, 'Juan P.', 4, 'Muy buen teléfono, la pantalla es hermosa y el sonido excelente.', '2024-06-25', 12, TRUE),
(1, 'Sofia M.', 5, 'Super rápido y con mucha memoria. Perfecto para mi trabajo.', '2024-07-05', 15, TRUE),

-- Reseñas para iPhone 15 Pro Max (Producto 2)
(2, 'Roberto S.', 5, 'El mejor iPhone que he tenido. La cámara es espectacular.', '2024-07-18', 20, TRUE),
(2, 'Carmen V.', 5, 'Increíble rendimiento y calidad de construcción premium.', '2024-07-12', 18, TRUE),
(2, 'Diego A.', 4, 'Excelente teléfono, aunque es caro. La cámara es increíble.', '2024-06-30', 14, TRUE),
(2, 'Laura F.', 5, 'Vale cada peso invertido. La batería dura muchísimo.', '2024-07-08', 16, TRUE),
(2, 'Miguel H.', 4, 'Muy buen producto, la calidad Apple se nota.', '2024-07-01', 11, TRUE),

-- Reseñas para Samsung Galaxy M55 5G (Producto 3)
(3, 'Patricia R.', 4, 'Buena opción de gama media. Cumple mis expectativas.', '2024-07-15', 7, TRUE),
(3, 'Fernando L.', 5, 'Excelente para el precio. Muy recomendado.', '2024-07-03', 9, TRUE),
(3, 'Isabel C.', 4, 'Buen teléfono, la pantalla es muy bonita.', '2024-06-28', 6, TRUE),
(3, 'Ricardo M.', 5, 'Super satisfecho con la compra. Muy buen rendimiento.', '2024-07-11', 13, TRUE),

-- Reseñas para Motorola Edge 50 Pro 5G (Producto 4)
(4, 'Alejandra P.', 4, 'Muy buen teléfono Motorola. El diseño es elegante.', '2024-07-14', 8, TRUE),
(4, 'Gabriel T.', 5, 'Excelente producto. La cámara es muy buena.', '2024-07-06', 10, TRUE),
(4, 'Valentina S.', 4, 'Buen rendimiento y precio accesible.', '2024-06-29', 7, TRUE),
(4, 'Andrés C.', 5, 'Muy satisfecho. La batería dura mucho.', '2024-07-09', 12, TRUE),

-- Reseñas para Smart TV Samsung 4K (Producto 5)
(5, 'Claudia M.', 5, 'Imagen espectacular en 4K. Muy satisfecha.', '2024-07-16', 15, TRUE),
(5, 'Hector R.', 4, 'Buena TV, el Smart funciona muy bien.', '2024-07-02', 9, TRUE),
(5, 'Natalia V.', 5, 'Excelente calidad de imagen y sonido.', '2024-06-27', 11, TRUE),

-- Reseñas para Auriculares Sony WH-1000XM5 (Producto 6)
(6, 'Daniel L.', 5, 'Los mejores auriculares que he probado. Cancelación de ruido increíble.', '2024-07-17', 22, TRUE),
(6, 'Carolina F.', 4, 'Muy buena calidad de sonido. Cómodos de usar.', '2024-07-04', 14, TRUE),
(6, 'Eduardo P.', 5, 'Excelente cancelación de ruido. Perfectos para el trabajo.', '2024-07-07', 18, TRUE),
(6, 'Monica S.', 4, 'Buenos auriculares, aunque son caros.', '2024-06-26', 8, TRUE);