package com.example.vocalist.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocalist.data.Repository
import com.example.vocalist.data.Vocalist
import com.example.vocalist.ui.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel (private val repository: Repository) : ViewModel() {
    private val _uiState : MutableStateFlow<UiState<Vocalist>> =
        MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<Vocalist>>
        get() = _uiState

    private val _isVocalistSaved = MutableStateFlow(false)
    val isVocalistSaved : StateFlow<Boolean> get() = _isVocalistSaved

    fun getVocalistById(vocalistId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getVocalistId(vocalistId))
        }
    }
    fun isFavorite(vocalistId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _isVocalistSaved.value = repository.isFavorite(vocalistId)
        }
    }

    fun deleteSavedVocalist(bookmarkedVocalist: Vocalist): Job {
        _isVocalistSaved.value = false
        return viewModelScope.launch(Dispatchers.IO) {
            repository.delete(bookmarkedVocalist)
        }
    }

    fun saveSavedVocalist(favoriteVocalist: Vocalist) {
        _isVocalistSaved.value = true
        viewModelScope.launch(Dispatchers.IO){
            repository.saveFavVocalist(favoriteVocalist)
        }
    }
}