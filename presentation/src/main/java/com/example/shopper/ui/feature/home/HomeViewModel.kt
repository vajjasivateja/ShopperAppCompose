package com.example.shopper.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Product
import com.example.domain.network.ResultWrapper
import com.example.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getProductsUseCase: GetProductsUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading
            getProductsUseCase.execute().let { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        val data = (result as ResultWrapper.Success).value
                        _uiState.value = HomeScreenUIEvents.Success(data)
                    }

                    is ResultWrapper.Failure -> {
                        val message = (result as ResultWrapper.Failure).exception.message
                            ?: "An error occured"
                        _uiState.value = HomeScreenUIEvents.Error(message)
                    }

                    else -> {
                        _uiState.value = HomeScreenUIEvents.Error("An unexpected error occured")
                    }
                }
            }
        }

    }
}


sealed class HomeScreenUIEvents {
    data object Loading : HomeScreenUIEvents()
    data class Success(val data: List<Product>) : HomeScreenUIEvents()
    data class Error(val message: String) : HomeScreenUIEvents()
}