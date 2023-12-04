package com.example.vocalist.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vocalist.ui.theme.MyVocalistComposeAppTheme

class MainActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MyVocalistComposeAppTheme {
                VocalistApp()
            }
        }
    }
}

