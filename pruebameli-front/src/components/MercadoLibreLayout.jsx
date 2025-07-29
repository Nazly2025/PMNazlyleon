import React from 'react';
import { ArrowLeft, Share2, Heart, Star, Truck, MapPin, Shield, RotateCcw, Award, Zap } from 'lucide-react';

export default function MercadoLibreLayout({ children, product, onBack }) {
  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header estilo Mercado Libre */}
      <header className="bg-white border-b border-gray-200 shadow-sm">
        <div className="ml-container py-3">
          <div className="flex justify-between items-center">
            {/* Logo y navegación */}
            <div className="flex items-center space-x-6">
              <button 
                onClick={onBack}
                className="flex items-center space-x-1 text-blue-600 hover:text-blue-800 transition-colors duration-200 group"
              >
                <ArrowLeft className="w-4 h-4 group-hover:-translate-x-1 transition-transform" />
                <span className="text-sm font-medium">Volver</span>
              </button>
              
              <div className="hidden md:flex items-center space-x-4 text-sm text-gray-600">
                <span className="hover:text-blue-600 cursor-pointer transition-colors">Celulares y Teléfonos</span>
                <span className="text-gray-400">›</span>
                <span className="hover:text-blue-600 cursor-pointer transition-colors">Celulares y Smartphones</span>
                {product?.category && (
                  <>
                    <span className="text-gray-400">›</span>
                    <span className="text-gray-800 font-medium">{product.category}</span>
                  </>
                )}
              </div>
            </div>
          </div>
        </div>
      </header>

      {/* Contenido principal */}
      <main className="ml-container py-6">
        {children}
      </main>
    </div>
  );
} 