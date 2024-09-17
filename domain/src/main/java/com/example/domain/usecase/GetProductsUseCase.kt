package com.example.domain.usecase

import com.example.domain.repository.ProductRepository

class GetProductsUseCase(private val productRepository: ProductRepository) {
    suspend fun execute() = productRepository.getProducts()
}