package com.github.vitorfg8.blogapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.vitorfg8.blogapp.ui.theme.BlogAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onPostClick: () -> Unit, onAddPostClick: () -> Unit
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(modifier = Modifier.shadow(4.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                titleContentColor = Color(0xFF32AAB8),
            ),
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontFamily = getGoogleFont("Quicksand"),
                    fontWeight = FontWeight.ExtraBold
                )
            })
    }, floatingActionButton = {
        FabNewPost {
            onAddPostClick()
        }
    }) {
        LazyColumn(
            contentPadding = it
        ) {
            items(emptyList<String>()) {
                PostItem(title = "", date = "") {
                    onPostClick()
                }
            }
        }
    }
}


private fun getGoogleFont(name: String): FontFamily {
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val fontName = GoogleFont(name)
    return FontFamily(
        Font(
            googleFont = fontName, fontProvider = provider
        )
    )
}

@Composable
private fun FabNewPost(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = { Icon(Icons.Filled.Add, stringResource(R.string.new_post)) },
        text = { Text(text = stringResource(R.string.new_post)) },
    )
}

@Composable
private fun PostItem(title: String, date: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(
                text = title,
                fontFamily = getGoogleFont("AR One Sans"),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp)
            )
            Text(text = date)
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    BlogAppTheme {
        HomeScreen(onPostClick = {}, onAddPostClick = {})
    }
}

@Preview
@Composable
private fun PostItemPreview() {
    BlogAppTheme {
        PostItem("Title", "9:00") {}
    }
}