package com.example.vocalist.view.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vocalist.data.Repository
import com.example.vocalist.data.Vocalist
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(private val repository: Repository): ViewModel() {
    private val _filteredVocalist = MutableStateFlow(
        repository.getVocalist()
            .sortedBy { it.name }
    )
    val filteredVocalist: StateFlow<List<Vocalist>> get() = _filteredVocalist

    private val _query = mutableStateOf("")
    val query: State<String> get() =_query
    fun search(newQuery: String) {
        _query.value = newQuery
        _filteredVocalist.value = repository.searchVocalist(_query.value)
            .sortedBy { it.name }
    }
}