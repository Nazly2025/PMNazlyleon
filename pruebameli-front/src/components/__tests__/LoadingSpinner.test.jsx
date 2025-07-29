import { render, screen } from '@testing-library/react'
import { describe, it, expect } from 'vitest'
import LoadingSpinner from '../LoadingSpinner'

describe('LoadingSpinner', () => {
  it('should render loading spinner with default message', () => {
    render(<LoadingSpinner />)
    
    expect(screen.getByText('Cargando...')).toBeInTheDocument()
    expect(screen.getByRole('status')).toBeInTheDocument()
  })

  it('should render loading spinner with custom message', () => {
    const customMessage = 'Cargando productos...'
    render(<LoadingSpinner message={customMessage} />)
    
    expect(screen.getByText(customMessage)).toBeInTheDocument()
  })

  it('should have correct CSS classes', () => {
    render(<LoadingSpinner />)
    
    const spinner = screen.getByRole('status')
    expect(spinner).toHaveClass('flex', 'flex-col', 'items-center', 'justify-center', 'p-8')
  })

  it('should render spinner animation', () => {
    render(<LoadingSpinner />)
    
    const spinnerElement = screen.getByTestId('spinner')
    expect(spinnerElement).toBeInTheDocument()
    expect(spinnerElement).toHaveClass('animate-spin')
  })
}) 