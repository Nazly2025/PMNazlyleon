// Página de detalle de producto estilo Mercado Libre
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import LoadingSpinner from "./components/LoadingSpinner";
import ErrorDisplay from "./components/ErrorDisplay";
import MercadoLibreLayout from "./components/MercadoLibreLayout";
import ProductGallery from "./components/ProductGallery";
import ProductFeatures from "./components/ProductFeatures";
import PurchasePanel from "./components/PurchasePanel";

export default function ProductPage() {
  // Obtiene el id de producto desde la URL
  const { id } = useParams();
  const navigate = useNavigate();

  // Estados principales
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Estados de UI
  const [selectedColor, setSelectedColor] = useState("negro");
  const [selectedImage, setSelectedImage] = useState(0);
  const [quantity, setQuantity] = useState(1);
  const [isWishlisted, setIsWishlisted] = useState(false);

  // Efecto para cargar el producto al montar/cambiar id
  useEffect(() => {
    const productId = id;
    // Validación básica
    if (!productId) {
      setLoading(false);
      return;
    }

    // Función asíncrona para obtener el producto
    const fetchProduct = async () => {
      setLoading(true);
      setError(null);

      try {
        // Llama al backend para obtener el producto
        const url = `http://localhost:8080/api/products/${productId}`;
        const response = await fetch(url, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
          },
          mode: 'cors',
        });

        if (!response.ok) {
          // Manejo de error HTTP
          const errorText = await response.text();
          throw new Error(
            `Error al cargar el producto: ${response.status} ${response.statusText} - ${errorText}`
          );
        }

        const data = await response.json();

        // Si el backend no envía features, colores, sellerInfo, etc., agrega datos dummy para evitar errores de renderizado
        // SUGERENCIA: Lo ideal es que el backend siempre envíe estos campos aunque estén vacíos
        if (!data.features || data.features.length === 0) {
          data.features = [
            { name: "Tamaño de la pantalla", value: "6.6\"" },
            { name: "Memoria interna", value: "256 GB" },
            { name: "Cámara trasera principal", value: "50 Mpx" },
            { name: "Memoria RAM", value: "8 GB" },
            { name: "Con NFC", value: "Sí" },
            { name: "Cámara frontal principal", value: "32 Mpx" }
          ];
        }
        if (!data.colors || data.colors.length === 0) {
          data.colors = [
            { name: "negro", hexCode: "#000000" },
            { name: "blanco", hexCode: "#FFFFFF" },
            { name: "azul", hexCode: "#0066CC" }
          ];
        }
        if (!data.sellerInfo) {
          data.sellerInfo = {
            name: "Tienda Oficial Samsung",
            reputationStatus: "MercadoLíder",
            totalSales: 15420
          };
        }
        if (!data.price) {
          data.price = 1299999;
          data.oldPrice = 1499999;
          data.discountPercentage = 13;
        }
        if (!data.installments) {
          data.installments = {
            count: 12,
            amount: 108333,
            interestRate: 0
          };
        }
        if (!data.rating) data.rating = 4.8;
        if (!data.reviewsCount) data.reviewsCount = 1247;
        if (!data.totalSales) data.totalSales = 15420;
        if (!data.availableStock) data.availableStock = 25;
        if (!data.ranking) data.ranking = 1;

        setProduct(data);

        // Selecciona color e imagen por defecto
        if (data.colors && data.colors.length > 0) {
          setSelectedColor(data.colors[0].name);
        }
        if (data.imageUrls && data.imageUrls.length > 0) {
          setSelectedImage(0);
        }
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchProduct();
  }, [id]);

  // Handlers de UI
  const handleQuantityChange = (newQuantity) => setQuantity(newQuantity);
  const handleWishlistToggle = () => setIsWishlisted((prev) => !prev);
  const handleColorChange = (colorName) => setSelectedColor(colorName);
  const handleImageSelect = (index) => setSelectedImage(index);
  const handleBuyNow = () => alert("Función de compra implementada");
  const handleAddToCart = () => alert("Producto agregado al carrito");
  const handleBack = () => navigate('/');

  // Renderizado condicional para carga y error
  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50">
        <LoadingSpinner size="large" text="Cargando producto..." />
      </div>
    );
  }
  if (error) {
    return (
      <ErrorDisplay 
        error={error}
        onRetry={() => window.location.reload()}
        onGoHome={() => navigate('/')}
      />
    );
  }
  if (!product) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50">
        <div className="text-center">
          <div className="bg-gray-100 rounded-full p-4 w-16 h-16 mx-auto mb-4 flex items-center justify-center">
            <svg className="w-8 h-8 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9.172 16.172a4 4 0 015.656 0M9 12h6m-6-4h6m2 5.291A7.962 7.962 0 0112 15c-2.34 0-4.47-.881-6.08-2.33" />
            </svg>
          </div>
          <h2 className="text-2xl font-bold text-gray-800 mb-2">Producto no encontrado</h2>
          <p className="text-gray-600 mb-4">El producto que buscas no existe o ha sido removido.</p>
          <button 
            onClick={() => navigate('/')}
            className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg transition-colors duration-200"
          >
            Volver al inicio
          </button>
        </div>
      </div>
    );
  }

  // Render principal
  const displayImages = product.imageUrls || [];
  const displayColors = product.colors || [];

  return (
    <MercadoLibreLayout product={product} onBack={handleBack}>
      <div className="grid grid-cols-1 lg:grid-cols-12 gap-6">
        {/* Columna izquierda: galería y características */}
        <div className="lg:col-span-8 space-y-6">
          <ProductGallery
            images={displayImages}
            selectedImage={selectedImage}
            onImageSelect={handleImageSelect}
            onWishlistToggle={handleWishlistToggle}
            isWishlisted={isWishlisted}
          />
          <ProductFeatures product={product} />
        </div>
        {/* Columna derecha: panel de compra */}
        <div className="lg:col-span-4">
          <div className="purchase-panel-sticky">
            <PurchasePanel
              product={product}
              quantity={quantity}
              onQuantityChange={handleQuantityChange}
              onBuyNow={handleBuyNow}
              onAddToCart={handleAddToCart}
            />
          </div>
        </div>
      </div>
    </MercadoLibreLayout>
  );
}