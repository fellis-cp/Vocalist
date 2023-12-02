package com.example.vocalist.view.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocalist.data.Repository
import com.example.vocalist.data.Vocalist
import com.example.vocalist.ui.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavViewModel (private val repository: Repository) : ViewModel() {
    private val _uiState :  MutableStateFlow<UiState<List<Vocalist>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Vocalist>>> get() = _uiState

    fun getFavoriteVocalist(){
        viewModelScope.launch (Dispatchers.IO){
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getFavVocalist().sortedBy { it.name })
        }
    }
}