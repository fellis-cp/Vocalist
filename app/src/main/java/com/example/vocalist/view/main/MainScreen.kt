package com.example.vocalist.view.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vocalist.Injection.Injection
import com.example.vocalist.factory.ViewModelFactory
import com.example.vocalist.ui.component.Search
import com.example.vocalist.ui.component.VocalistList
import com.example.vocalist.ui.theme.MyVocalistComposeAppTheme

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
//            Image(
//                painter = painterResource(R.drawable.banner),
//                contentDescription = "Banner Image",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.height(160.dp)
//            )
            Search(query = query, onQueryChange = viewModel::search)
        }
        VocalistList(vocalist = filteredVocalist, navigateToDetail = navigateToDetail)
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MyVocalistComposeAppTheme {
        MainScreen(navigateToDetail = {})
    }
}