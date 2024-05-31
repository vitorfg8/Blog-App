package com.github.vitorfg8.blogapp.ui.postdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.github.vitorfg8.blogapp.R
import com.github.vitorfg8.blogapp.ui.theme.BlogAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailsScreen(uiState: PostDetailsUiState, onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("") }, navigationIcon = {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            })
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                text = uiState.title,
                style = MaterialTheme.typography.displaySmall,
                fontFamily = getGoogleFont()
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                text = uiState.date,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp), text = uiState.description
            )
        }
    }
}

private fun getGoogleFont(): FontFamily {
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val fontName = GoogleFont("AR One Sans")
    return FontFamily(
        Font(
            googleFont = fontName, fontProvider = provider
        )
    )
}


@Preview
@Composable
private fun PostDetailsScreenPreview(@PreviewParameter(LoremIpsum::class) text: String) {
    BlogAppTheme {
        PostDetailsScreen(
            PostDetailsUiState(
                title = "Hello World!",
                description = text,
                date = "31/05/2024 às 09:00"
            )
        ) {}
    }
}