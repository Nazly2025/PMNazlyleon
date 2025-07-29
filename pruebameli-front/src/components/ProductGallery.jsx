import React from 'react';
import { Heart, Zap, ChevronLeft, ChevronRight } from 'lucide-react';

export default function ProductGallery({ 
  images = [], 
  selectedImage, 
  onImageSelect, 
  onWishlistToggle, 
  isWishlisted 
}) {
  const handlePrevious = () => {
    if (selectedImage > 0) {
      onImageSelect(selectedImage - 1);
    }
  };

  const handleNext = () => {
    if (selectedImage < images.length - 1) {
      onImageSelect(selectedImage + 1);
    }
  };

  // Mostrar solo las primeras 4 imágenes (1 principal + 3 miniaturas)
  const displayImages = images.slice(0, 4);
  const hasMultipleImages = displayImages.length > 1;

  return (
    <div className="ml-card">
      {/* Galería principal */}
      <div className="relative product-gallery">
        {/* Imagen principal */}
        <div className="relative main-image bg-white">
          {displayImages.length > 0 ? (
            <img
              src={displayImages[selectedImage]}
              alt="Producto"
              className="w-full h-full object-contain p-4"
            />
          ) : (
            <div className="w-full h-full flex items-center justify-center bg-gray-100">
              <span className="text-gray-400">Sin imagen</span>
            </div>
          )}
          
          {/* Botón de wishlist */}
          <button
            onClick={onWishlistToggle}
            className="absolute top-4 right-4 p-2 bg-white/90 backdrop-blur-sm rounded-full shadow-lg hover:shadow-xl transition-all duration-200"
          >
            <Heart
              className={`w-5 h-5 ${
                isWishlisted 
                  ? "text-red-500 fill-red-500" 
                  : "text-gray-400 hover:text-red-400"
              }`}
            />
          </button>

          {/* Badge de más vendido */}
          <div className="absolute top-4 left-4">
            <span className="flex items-center bg-orange-500 text-white text-xs font-bold px-2 py-1 rounded-full">
              <Zap className="w-3 h-3 mr-1" />
              MÁS VENDIDO
            </span>
          </div>

          {/* Navegación de imágenes - solo si hay múltiples imágenes */}
          {hasMultipleImages && (
            <>
              <button
                onClick={handlePrevious}
                disabled={selectedImage === 0}
                className="absolute left-2 top-1/2 -translate-y-1/2 bg-white/80 hover:bg-white rounded-full p-2 shadow-lg disabled:opacity-50 disabled:cursor-not-allowed transition-all duration-200"
              >
                <ChevronLeft className="w-5 h-5 text-gray-600" />
              </button>
              <button
                onClick={handleNext}
                disabled={selectedImage === displayImages.length - 1}
                className="absolute right-2 top-1/2 -translate-y-1/2 bg-white/80 hover:bg-white rounded-full p-2 shadow-lg disabled:opacity-50 disabled:cursor-not-allowed transition-all duration-200"
              >
                <ChevronRight className="w-5 h-5 text-gray-600" />
              </button>
            </>
          )}
        </div>

        {/* Miniaturas - máximo 3 */}
        {hasMultipleImages && (
          <div className="p-4 border-t border-gray-100">
            <div className="flex gap-3 justify-center">
              {displayImages.slice(0, 3).map((image, index) => (
                <button
                  key={index}
                  onClick={() => onImageSelect(index)}
                  className={`flex-shrink-0 w-20 h-20 border-2 rounded-lg transition-all duration-200 ${
                    selectedImage === index
                      ? "border-blue-500 ring-2 ring-blue-200"
                      : "border-gray-200 hover:border-gray-300"
                  }`}
                >
                  <img
                    src={image}
                    alt={`Vista ${index + 1}`}
                    className="w-full h-full object-contain p-1 rounded"
                  />
                </button>
              ))}
            </div>
          </div>
        )}
      </div>
    </div>
  );
} 