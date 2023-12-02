package com.example.vocalist.ui

sealed class UiState <out T : Any?> {
    data class  Error(val message : String) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<out T : Any>(val data : T) : UiState<T>()
}