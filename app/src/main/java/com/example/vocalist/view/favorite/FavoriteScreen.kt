package com.example.vocalist.view.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vocalist.Injection.Injection
import com.example.vocalist.factory.ViewModelFactory
import com.example.vocalist.ui.UiState
import com.example.vocalist.ui.component.VocalistList

@Composable
fun FavoriteScreen(
    viewModel: FavViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getFavoriteVocalist()
            }


            is UiState.Success -> {
                val favoriteVocalist = uiState.data
                VocalistList(vocalist = favoriteVocalist, navigateToDetail = navigateToDetail)
            }

            is UiState.Error -> {}
        }
    }
}