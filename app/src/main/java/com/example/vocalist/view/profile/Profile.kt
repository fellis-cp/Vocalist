package com.example.vocalist.view.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vocalist.R
import com.example.vocalist.ui.theme.MyVocalistComposeAppTheme
import com.example.vocalist.ui.theme.Typography

@Composable
fun Profile() {
    Column(
        Modifier.fillMaxSize().padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        AsyncImage(
            model = R.drawable.hanif,
            contentDescription = stringResource(R.string.namaku),
            Modifier
                .size(250.dp)
                .align(Alignment.CenterHorizontally)
                .padding(14.dp)
                .clip(CircleShape)
        )
        Text(
            stringResource(R.string.namaku),
            style = Typography.titleMedium
        )
        Text(
            stringResource(R.string.gmail),
            style = Typography.titleSmall)

    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MyVocalistComposeAppTheme {
        Profile()
    }
}