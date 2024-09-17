package com.example.data.di

import com.example.domain.repository.ProductRepository
import com.example.data.repository.ProductRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
}