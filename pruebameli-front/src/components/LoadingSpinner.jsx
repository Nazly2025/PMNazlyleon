import React from 'react';

export default function LoadingSpinner({ size = "default", text = "Cargando..." }) {
  const sizeClasses = {
    small: "w-8 h-8",
    default: "w-16 h-16",
    large: "w-24 h-24"
  };

  return (
    <div className="flex flex-col items-center justify-center p-8" role="status">
      <div className={`${sizeClasses[size]} relative`} data-testid="spinner">
        {/* Spinner principal */}
        <div className="absolute inset-0 rounded-full border-4 border-blue-200"></div>
        <div className="absolute inset-0 rounded-full border-4 border-transparent border-t-blue-600 animate-spin"></div>
        
        {/* Punto central */}
        <div className="absolute inset-0 flex items-center justify-center">
          <div className="w-2 h-2 bg-blue-600 rounded-full animate-pulse"></div>
        </div>
      </div>
      
      {text && (
        <p className="mt-4 text-gray-600 font-medium animate-pulse">
          {text}
        </p>
      )}
    </div>
  );
} 