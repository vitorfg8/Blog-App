package com.github.vitorfg8.blogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.github.vitorfg8.blogapp.ui.HomeScreen
import com.github.vitorfg8.blogapp.ui.theme.BlogAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlogAppTheme {
                HomeScreen()
            }
        }
    }
}