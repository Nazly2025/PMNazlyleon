import React from 'react';
import { useNavigate } from 'react-router-dom';

export default function App() {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen bg-gray-50 flex items-center justify-center">
      <div className="text-center">
        <div className="bg-white p-8 rounded-lg shadow-lg">
          <h1 className="text-4xl font-bold text-gray-900 mb-6">
            Mercado Libre Product Page
          </h1>
          <p className="text-gray-600 mb-8">
            Demo de página de producto estilo Mercado Libre
          </p>
          <div className="space-y-4">
            <button
              onClick={() => navigate('/product/1')}
              className="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 px-6 rounded-lg transition-colors duration-200 block w-full"
            >
              Ver Producto Demo
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}