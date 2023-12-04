package com.example.vocalist.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vocalist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.Cyan
    ) {
        SearchBar(
            onSearch = {},
            active = false,
            onActiveChange = {},
            query = query,
            onQueryChange = onQueryChange,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Blue
                )
            },
            placeholder = {
                Text(text = stringResource(R.string.search_hint))
            },
            shape = MaterialTheme.shapes.large,
            modifier = modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .padding(8.dp)

        ) {}
    }
}