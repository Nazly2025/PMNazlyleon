import React from 'react';
import { AlertCircle, RefreshCw, Home } from 'lucide-react';

export default function ErrorDisplay({ 
  error, 
  onRetry, 
  onGoHome,
  title = "Error al cargar el producto",
  showSolution = true 
}) {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-red-50 to-pink-100" role="status">
      <div className="max-w-md mx-auto text-center p-8">
        {/* Icono de error animado */}
        <div className="bg-red-100 rounded-full p-6 w-20 h-20 mx-auto mb-6 flex items-center justify-center animate-bounce">
          <AlertCircle className="w-10 h-10 text-red-600" data-testid="error-icon" />
        </div>
        
        {/* Título */}
        <h2 className="text-2xl font-bold text-red-800 mb-4">
          {title}
        </h2>
        
        {/* Mensaje de error */}
        <p className="text-red-600 mb-6 text-sm">
          {error}
        </p>
        
        {/* Solución */}
        {showSolution && (
          <div className="bg-white rounded-lg p-4 border border-red-200 mb-6">
            <p className="text-sm text-gray-700 mb-2">
              <span className="font-semibold">Solución:</span> Asegúrate de que tu backend esté corriendo en:
            </p>
            <code className="bg-gray-100 px-3 py-1 rounded text-sm font-mono text-blue-600">
              http://localhost:8080
            </code>
          </div>
        )}
        
        {/* Botones de acción */}
        <div className="flex gap-3 justify-center">
          {onRetry && (
            <button
              onClick={onRetry}
              className="flex items-center gap-2 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg transition-colors duration-200"
            >
              <RefreshCw className="w-4 h-4" />
              Reintentar
            </button>
          )}
          
          {onGoHome && (
            <button
              onClick={onGoHome}
              className="flex items-center gap-2 bg-gray-600 hover:bg-gray-700 text-white px-4 py-2 rounded-lg transition-colors duration-200"
            >
              <Home className="w-4 h-4" />
              Ir al inicio
            </button>
          )}
        </div>
      </div>
    </div>
  );
} 