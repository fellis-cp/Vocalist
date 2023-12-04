package com.example.vocalist.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.vocalist.data.Vocalist

@Composable
fun VocalistList(
    vocalist: List<Vocalist>,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier.testTag("VocalistList")
        ) {
            items(items = vocalist,
                key = { it.id }) { vocalist ->
                VocalistItem(
                    name = vocalist.name,
                    picture = vocalist.picture,
                    band = vocalist.band,
                    genre = vocalist.genre,
                    modifier = Modifier.clickable {
                        navigateToDetail(vocalist.id)
                    }
                )
            }
        }
    }
}

