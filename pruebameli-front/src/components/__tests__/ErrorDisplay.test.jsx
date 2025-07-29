import { render, screen, fireEvent } from '@testing-library/react'
import { describe, it, expect, vi } from 'vitest'
import ErrorDisplay from '../ErrorDisplay'

// Mock de useNavigate
const mockNavigate = vi.fn()
vi.mock('react-router-dom', () => ({
  useNavigate: () => mockNavigate
}))

describe('ErrorDisplay', () => {
  beforeEach(() => {
    mockNavigate.mockClear()
  })

  it('should render error message', () => {
    const errorMessage = 'Error al cargar el producto'
    render(<ErrorDisplay error={errorMessage} />)
    
    expect(screen.getByText(errorMessage)).toBeInTheDocument()
  })

  it('should render default error message when no error provided', () => {
    render(<ErrorDisplay />)
    
    expect(screen.getByText('Ha ocurrido un error')).toBeInTheDocument()
  })

  it('should render retry button', () => {
    const onRetry = vi.fn()
    render(<ErrorDisplay onRetry={onRetry} />)
    
    const retryButton = screen.getByRole('button', { name: /reintentar/i })
    expect(retryButton).toBeInTheDocument()
  })

  it('should call onRetry when retry button is clicked', () => {
    const onRetry = vi.fn()
    render(<ErrorDisplay onRetry={onRetry} />)
    
    const retryButton = screen.getByRole('button', { name: /reintentar/i })
    fireEvent.click(retryButton)
    
    expect(onRetry).toHaveBeenCalledTimes(1)
  })

  it('should render home button', () => {
    render(<ErrorDisplay />)
    
    const homeButton = screen.getByRole('button', { name: /ir al inicio/i })
    expect(homeButton).toBeInTheDocument()
  })

  it('should navigate to home when home button is clicked', () => {
    render(<ErrorDisplay />)
    
    const homeButton = screen.getByRole('button', { name: /ir al inicio/i })
    fireEvent.click(homeButton)
    
    expect(mockNavigate).toHaveBeenCalledWith('/')
  })

  it('should have correct CSS classes', () => {
    render(<ErrorDisplay />)
    
    const container = screen.getByRole('status')
    expect(container).toHaveClass('flex', 'flex-col', 'items-center', 'justify-center', 'p-8')
  })

  it('should render error icon', () => {
    render(<ErrorDisplay />)
    
    const errorIcon = screen.getByTestId('error-icon')
    expect(errorIcon).toBeInTheDocument()
  })
}) 