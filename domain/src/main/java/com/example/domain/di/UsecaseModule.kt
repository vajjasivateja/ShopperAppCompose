package com.example.domain.di

import com.example.domain.usecase.GetProductsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductsUseCase(get()) }
}