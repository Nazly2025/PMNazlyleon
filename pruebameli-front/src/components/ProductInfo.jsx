import React from 'react';
import { Star, ChevronRight } from 'lucide-react';

export default function ProductInfo({ product, selectedColor, onColorChange, colors = [] }) {
  return (
    <div className="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
      {/* Estado del producto */}
      <div className="flex items-center gap-2 mb-3">
        <span className="status-badge new">
          Nuevo
        </span>
        <span className="text-gray-500 text-sm">•</span>
        <span className="text-gray-600 text-sm">+{product?.totalSales || 0} vendidos</span>
      </div>

      {/* Título del producto */}
      <h1 className="text-2xl font-light text-gray-900 mb-4 leading-tight">
        {product?.name || "Nombre del producto"}
      </h1>

      {/* Rating */}
      <div className="flex items-center gap-3 mb-4">
        <div className="flex items-center bg-blue-50 px-3 py-1.5 rounded-lg">
          <span className="text-lg font-bold text-gray-900 mr-2">
            {product?.rating ? product.rating.toFixed(1) : "0.0"}
          </span>
          <div className="flex">
            {Array.from({ length: 5 }, (_, i) => (
              <Star
                key={i}
                className={`w-4 h-4 ${
                  i < Math.floor(product?.rating || 0)
                    ? "fill-yellow-400 text-yellow-400"
                    : "text-gray-300"
                }`}
              />
            ))}
          </div>
        </div>
        <button className="text-blue-600 hover:text-blue-800 text-sm font-medium hover:underline">
          ({product?.reviewsCount || 0} opiniones)
        </button>
      </div>

      {/* Ranking */}
      {product?.ranking && (
        <div className="mb-4">
          <span className="status-badge ranking">
            🏆 {product.ranking}° en {product.category || "Categoría"}
          </span>
        </div>
      )}

      {/* Selección de color */}
      {colors.length > 0 && (
        <div className="mb-6">
          <div className="flex items-center gap-2 mb-3">
            <span className="text-sm font-medium text-gray-700">Color:</span>
            <span className="text-sm font-semibold text-blue-600 capitalize">
              {selectedColor}
            </span>
          </div>
          <div className="flex gap-2">
            {colors.map((color) => (
              <button
                key={color.name}
                onClick={() => onColorChange(color.name)}
                className={`w-12 h-12 rounded-full border-2 transition-all duration-200 ${
                  selectedColor === color.name
                    ? "border-blue-500 ring-2 ring-blue-200"
                    : "border-gray-200 hover:border-gray-300"
                }`}
                style={{ backgroundColor: color.hexCode }}
                title={color.name}
              >
                {selectedColor === color.name && (
                  <div className="w-full h-full flex items-center justify-center">
                    <div className="w-6 h-6 rounded-full bg-white bg-opacity-90 flex items-center justify-center">
                      <ChevronRight className="w-3 h-3 text-gray-800" />
                    </div>
                  </div>
                )}
              </button>
            ))}
          </div>
        </div>
      )}

      {/* Características principales */}
      {product?.features && product.features.length > 0 && (
        <div className="border-t border-gray-100 pt-4">
          <h3 className="text-lg font-semibold text-gray-900 mb-3">
            Características principales
          </h3>
          <div className="space-y-2">
            {product.features.map((feature, index) => (
              <div key={index} className="flex items-start gap-2 text-sm text-gray-700">
                <div className="w-1.5 h-1.5 bg-blue-500 rounded-full mt-2 flex-shrink-0"></div>
                <span>{feature.name}: {feature.value}</span>
              </div>
            ))}
          </div>
          <button className="text-blue-600 hover:text-blue-800 text-sm font-medium mt-3 hover:underline flex items-center gap-1">
            Ver todas las características
            <ChevronRight className="w-3 h-3" />
          </button>
        </div>
      )}

      {/* Descripción */}
      {product?.description && (
        <div className="border-t border-gray-100 pt-4 mt-4">
          <h3 className="text-lg font-semibold text-gray-900 mb-3">
            Descripción
          </h3>
          <p className="text-gray-700 text-sm leading-relaxed">
            {product.description}
          </p>
        </div>
      )}
    </div>
  );
} 