package com.example.vocalist.view.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.vocalist.Injection.Injection
import com.example.vocalist.R
import com.example.vocalist.factory.ViewModelFactory
import com.example.vocalist.ui.UiState
import com.example.vocalist.ui.component.HeightSpacer
import com.example.vocalist.ui.component.MyTopAppBar
import com.example.vocalist.ui.theme.MyVocalistComposeAppTheme
import com.example.vocalist.ui.theme.Typography

@Composable
fun DetailScreen(
    vocalistId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current)
        )
    ),
    navigateBack: () -> Unit
) {
    val isVocalistSaved by viewModel.isVocalistSaved.collectAsState()

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getVocalistById(vocalistId)
                viewModel.isFavorite(vocalistId)
            }

            is UiState.Error -> {}

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    name = data.name,
                    fullname = data.fullname,
                    picture = data.picture,
                    band = data.band,
                    birthdate = data.birthdate,
                    genre = data.genre,
                    description = data.description,
                    isVocalistSaved,
                    setFavorite = {
                        if (!isVocalistSaved) {
                            viewModel.saveSavedVocalist(data)
                        } else {
                            viewModel.deleteSavedVocalist(data)
                        }
                    },
                    onBackClick = navigateBack
                )
            }
        }
    }
}

@Composable
fun DetailContent(
    name: String,
    fullname: String,
    picture: Int,
    band: String,
    birthdate: String,
    genre: String,
    description: String,
    isVocalistSaved: Boolean,
    setFavorite: () -> Unit,
    onBackClick: () -> Unit
) {
    Column {
        MyTopAppBar(screenName = R.string.detail, onBackClick)
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = setFavorite,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (isVocalistSaved) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
                            ),
                            tint = if (isVocalistSaved) Color.Red else Color.Gray,
                            contentDescription = stringResource(R.string.favorite_button),
                        )
                    }
                    Text(
                        text = name,
                        modifier = Modifier.align(Alignment.Center),
                        style = Typography.titleLarge,
                    )
                }
            }
            HeightSpacer(height = 16.dp)
            AsyncImage(
                model = picture, contentDescription = name, modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
            )
            HeightSpacer(height = 16.dp)
            Text(
                text = stringResource(R.string.full_name, fullname),
                style = Typography.bodyLarge
            )
            HeightSpacer(height = 8.dp)
            Text(
                text = stringResource(R.string.band, band),
                style = Typography.bodyLarge
            )
            HeightSpacer(height = 8.dp)
            Text(
                text = stringResource(R.string.birthdate, birthdate),
                style = Typography.bodyLarge
            )
            HeightSpacer(height = 8.dp)
            Text(
                text = stringResource(R.string.genre, genre),
                style = Typography.bodyLarge
            )
            HeightSpacer(height = 16.dp)
            Text(
                text = stringResource(R.string.description),
                fontWeight = FontWeight.Bold,
                style = Typography.bodyLarge
            )
            HeightSpacer(height = 8.dp)
            Text(text = description, textAlign = TextAlign.Justify, style = Typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    MyVocalistComposeAppTheme {
        DetailScreen(vocalistId = 6) {
        }
    }

}