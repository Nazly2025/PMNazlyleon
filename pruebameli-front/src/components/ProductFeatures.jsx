import React from 'react';
import { ChevronRight, Star } from 'lucide-react';

export default function ProductFeatures({ product }) {
  return (
    <div className="space-y-6">
      {/* Características principales */}
      {product?.features && product.features.length > 0 && (
        <div className="ml-card p-6">
          <h3 className="text-xl font-semibold text-gray-900 mb-4">
            Características principales
          </h3>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            {product.features.map((feature, index) => (
              <div key={index} className="flex items-start gap-3 p-3 bg-gray-50 rounded-lg hover:bg-gray-100 transition-colors duration-200">
                <div className="w-2 h-2 bg-blue-500 rounded-full mt-2 flex-shrink-0"></div>
                <div className="text-sm text-gray-700">
                  <span className="font-medium">{feature.name}:</span> {feature.value}
                </div>
              </div>
            ))}
          </div>
          <button className="text-blue-600 hover:text-blue-800 font-medium transition-colors hover:underline text-sm flex items-center gap-1 mt-4">
            Ver todas las características
            <ChevronRight className="w-4 h-4" />
          </button>
        </div>
      )}

      {/* Reseñas */}
      <div className="ml-card p-6">
        <div className="flex items-center justify-between mb-4">
          <h3 className="text-xl font-semibold text-gray-900">
            Opiniones de compradores
          </h3>
          <div className="flex items-center gap-3">
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
            <span className="text-sm text-gray-600">
              {product?.rating ? product.rating.toFixed(1) : "0.0"}
            </span>
            <span className="text-xs text-gray-500">
              ({product?.reviewsCount || 0} opiniones)
            </span>
          </div>
        </div>

        <div className="space-y-4">

          {/* Lista de reseñas reales */}
          {product?.reviews && product.reviews.length > 0 && (
            <div className="space-y-4">
              <h4 className="font-medium text-gray-900">Últimas opiniones</h4>
              {product.reviews.slice(0, 3).map((review, index) => (
                <div key={index} className="border border-gray-200 rounded-lg p-4 bg-white">
                  <div className="flex items-center justify-between mb-2">
                    <div className="flex items-center gap-2">
                      <div className="flex">
                        {Array.from({ length: 5 }, (_, i) => (
                          <Star
                            key={i}
                            className={`w-3 h-3 ${
                              i < review.rating
                                ? "fill-yellow-400 text-yellow-400"
                                : "text-gray-300"
                            }`}
                          />
                        ))}
                      </div>
                      <span className="text-sm font-medium text-gray-900">
                        {review.userName}
                      </span>
                      {review.verified && (
                        <span className="text-xs bg-green-100 text-green-800 px-2 py-1 rounded-full">
                          Compra verificada
                        </span>
                      )}
                    </div>
                    <span className="text-xs text-gray-500">
                      {review.date ? new Date(review.date).toLocaleDateString('es-ES') : 'Fecha no disponible'}
                    </span>
                  </div>
                  <p className="text-sm text-gray-700 mb-2">{review.comment}</p>
                  {review.helpful > 0 && (
                    <div className="text-xs text-gray-500">
                      A {review.helpful} personas les resultó útil
                    </div>
                  )}
                </div>
              ))}
            </div>
          )}

          {/* Botón para ver todas las reseñas */}
          <button className="w-full text-blue-600 hover:text-blue-800 font-medium transition-colors hover:underline text-sm flex items-center justify-center gap-1 py-2 border border-blue-200 rounded-lg hover:bg-blue-50">
            Ver todas las opiniones
            <ChevronRight className="w-4 h-4" />
          </button>
        </div>
      </div>

      {/* Descripción del producto */}
      {product?.description && (
        <div className="ml-card p-6">
          <h3 className="text-xl font-semibold text-gray-900 mb-4">
            Descripción
          </h3>
          <p className="text-gray-700 leading-relaxed text-sm">
            {product.description}
          </p>
        </div>
      )}
    </div>
  );
} 