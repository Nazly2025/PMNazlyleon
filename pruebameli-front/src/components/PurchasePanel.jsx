import React from 'react';
import { 
  Truck, 
  MapPin, 
  Award, 
  Shield, 
  RotateCcw, 
  Plus, 
  Minus, 
  ShoppingCart,
  CreditCard,
  Calendar,
  Package,
  CheckCircle,
  Star,
  User
} from 'lucide-react';

export default function PurchasePanel({ 
  product, 
  quantity, 
  onQuantityChange,
  onBuyNow,
  onAddToCart 
}) {
  const handleQuantityDecrease = () => {
    if (quantity > 1) {
      onQuantityChange(quantity - 1);
    }
  };

  const handleQuantityIncrease = () => {
    onQuantityChange(quantity + 1);
  };

  return (
    <div className="ml-card p-6 space-y-4">
      {/* Nombre del producto */}
      <div className="space-y-2">
        <h1 className="text-2xl font-light text-gray-900 leading-tight">
          {product?.name || "Nombre del producto"}
        </h1>
        <div className="flex items-center gap-3 text-sm text-gray-600">
          <span className="status-badge new">Nuevo</span>
          <span>•</span>
          <span>+{product?.totalSales || 0} vendidos</span>
        </div>
      </div>

      {/* Precio */}
      <div className="space-y-2">
        {product?.oldPrice && (
          <div className="text-lg text-gray-500 line-through">
            $ {product.oldPrice.toLocaleString("es-CO")}
          </div>
        )}
        
        <div className="flex items-end gap-3">
          <span className="ml-price">
            $ {product?.price ? product.price.toLocaleString("es-CO") : "N/A"}
          </span>
          {product?.discountPercentage && (
            <span className="ml-discount">
              {product.discountPercentage}% OFF
            </span>
          )}
        </div>

        {product?.installments && (
          <div className="text-sm text-green-700 font-medium">
            {product.installments.count} cuotas de ${" "}
            {product.installments.amount.toLocaleString("es-CO")}
            {product.installments.interestRate > 0 && (
              <span className="text-gray-600">
                {" "}con {product.installments.interestRate}% interés
              </span>
            )}
          </div>
        )}

        <button className="text-blue-600 hover:text-blue-800 text-sm font-medium hover:underline flex items-center gap-1">
          <CreditCard className="w-4 h-4" />
          Ver los medios de pago
        </button>
      </div>

      <hr className="border-gray-200" />

      {/* Entrega */}
      <div className="space-y-3">
        <h3 className="font-semibold text-gray-900">Entrega</h3>
        
        <div className="space-y-2">
          <div className="flex items-start gap-3 p-3 bg-green-50 rounded-lg border border-green-200">
            <Truck className="w-5 h-5 text-green-600 mt-0.5 flex-shrink-0" />
            <div>
              <div className="font-medium text-green-700 text-sm">
                Llega gratis mañana{" "}
                {new Date().toLocaleDateString("es-CO", { weekday: "long" })}
              </div>
              <button className="text-blue-600 hover:text-blue-800 text-xs mt-1 hover:underline">
                Más formas de entrega
              </button>
            </div>
          </div>

          <div className="flex items-start gap-3 p-3 bg-blue-50 rounded-lg border border-blue-200">
            <MapPin className="w-5 h-5 text-blue-600 mt-0.5 flex-shrink-0" />
            <div>
              <div className="font-medium text-blue-700 text-sm">
                Retira gratis desde el{" "}
                {new Date(
                  new Date().setDate(new Date().getDate() + 1)
                ).toLocaleDateString("es-CO", { weekday: "long" })}
              </div>
              <button className="text-blue-600 hover:text-blue-800 text-xs mt-1 hover:underline">
                Ver en el mapa
              </button>
            </div>
          </div>
        </div>
      </div>

      <hr className="border-gray-200" />

      {/* Stock */}
      <div className="space-y-3">
        <h3 className="font-semibold text-gray-900">Stock</h3>
        
        <div className="flex items-center gap-3 p-3 bg-gray-50 rounded-lg">
          <Package className="w-5 h-5 text-gray-600" />
          <div>
            <div className="font-medium text-gray-700 text-sm">
              {product?.availableStock || 0} unidades disponibles
            </div>
            <div className="text-xs text-gray-500">
              Envío inmediato
            </div>
          </div>
        </div>

        {/* Cantidad */}
        <div className="space-y-2">
          <div className="text-sm font-medium text-gray-700">
            Cantidad: {quantity} unidad{quantity !== 1 ? 'es' : ''}
          </div>
          <div className="flex items-center border border-gray-300 rounded-lg w-fit">
            <button
              onClick={handleQuantityDecrease}
              disabled={quantity <= 1}
              className="p-2 hover:bg-gray-100 transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
            >
              <Minus className="w-4 h-4 text-gray-600" />
            </button>
            <span className="px-4 py-2 font-medium text-gray-800">
              {quantity}
            </span>
            <button
              onClick={handleQuantityIncrease}
              disabled={quantity >= (product?.availableStock || 1)}
              className="p-2 hover:bg-gray-100 transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
            >
              <Plus className="w-4 h-4 text-gray-600" />
            </button>
          </div>
        </div>
      </div>

      {/* Botones de compra */}
      <div className="space-y-2">
        <button
          onClick={onBuyNow}
          className="w-full ml-button-primary py-3 text-lg"
        >
          Comprar ahora
        </button>
        <button
          onClick={onAddToCart}
          className="w-full ml-button-secondary py-3 text-lg flex items-center justify-center gap-2"
        >
          <ShoppingCart className="w-5 h-5" />
          Agregar al carrito
        </button>
      </div>

      <hr className="border-gray-200" />

      {/* Información del vendedor */}
      <div className="space-y-3">
        <h3 className="font-semibold text-gray-900">Vendedor</h3>
        
        <div className="space-y-2">
          <div className="flex items-start gap-3 p-3 bg-yellow-50 rounded-lg border border-yellow-200">
            <User className="w-5 h-5 text-yellow-600 mt-1 flex-shrink-0" />
            <div className="flex-1">
              <div className="text-sm mb-2">
                Vendido por{" "}
                <span className="text-blue-600 font-medium">
                  {product?.sellerInfo?.name || "Vendedor"}
                </span>
              </div>
              {product?.sellerInfo && (
                <div className="text-xs text-gray-600 space-y-2">
                  {/* Rating del vendedor */}
                  <div className="flex items-center gap-1">
                    <Star className="w-3 h-3 text-yellow-600" />
                    <span className="font-medium">{product.sellerInfo.rating?.toFixed(1) || "N/A"} de 5 estrellas</span>
                  </div>
                  
                  {/* Total de ventas */}
                  {product.sellerInfo.totalSales && (
                    <div className="flex items-center gap-1">
                      <Award className="w-3 h-3 text-yellow-600" />
                      <span>+{product.sellerInfo.totalSales.toLocaleString("es-CO")} ventas</span>
                    </div>
                  )}
                  
                  {/* Años activo */}
                  {product.sellerInfo.yearsActive && (
                    <div className="flex items-center gap-1">
                      <Calendar className="w-3 h-3 text-yellow-600" />
                      <span>{product.sellerInfo.yearsActive} años vendiendo</span>
                    </div>
                  )}
                  
                  {/* Ubicación */}
                  {product.sellerInfo.location && (
                    <div className="flex items-center gap-1">
                      <MapPin className="w-3 h-3 text-yellow-600" />
                      <span>{product.sellerInfo.location}</span>
                    </div>
                  )}
                  
                  {/* Badges del vendedor */}
                  {product.sellerInfo.badges && product.sellerInfo.badges.length > 0 && (
                    <div className="flex flex-wrap gap-1 mt-2">
                      {product.sellerInfo.badges.map((badge, index) => (
                        <span 
                          key={index}
                          className="px-2 py-1 bg-blue-100 text-blue-800 text-xs rounded-full"
                        >
                          {badge}
                        </span>
                      ))}
                    </div>
                  )}
                </div>
              )}
            </div>
          </div>
        </div>
      </div>

      <hr className="border-gray-200" />

      {/* Garantías */}
      <div className="space-y-3">
        <h3 className="font-semibold text-gray-900">Garantías</h3>
        
        <div className="space-y-2">
          <div className="flex items-start gap-3 p-3 bg-green-50 rounded-lg border border-green-200">
            <RotateCcw className="w-5 h-5 text-green-600 mt-1 flex-shrink-0" />
            <div>
              <span className="font-medium text-green-700 text-sm">
                Devolución gratis.
              </span>{" "}
              <span className="text-green-600 text-sm">
                Tienes 30 días desde que lo recibes.
              </span>
            </div>
          </div>
          
          <div className="flex items-start gap-3 p-3 bg-blue-50 rounded-lg border border-blue-200">
            <Shield className="w-5 h-5 text-blue-600 mt-1 flex-shrink-0" />
            <div>
              <span className="font-medium text-blue-700 text-sm">
                Compra Protegida,
              </span>{" "}
              <span className="text-blue-600 text-sm">
                recibe el producto que esperabas o te devolvemos tu dinero.
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
} 