package com.example.vocalist.view.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vocalist.Injection.Injection
import com.example.vocalist.factory.ViewModelFactory
import com.example.vocalist.ui.component.Search
import com.example.vocalist.ui.component.VocalistList

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val filteredVocalist by viewModel.filteredVocalist.collectAsState()
    val query by viewModel.query
    Column {
        Box {
            Search(query = query, onQueryChange = viewModel::search)
        }
        VocalistList(vocalist = filteredVocalist, navigateToDetail = navigateToDetail)
    }
}

