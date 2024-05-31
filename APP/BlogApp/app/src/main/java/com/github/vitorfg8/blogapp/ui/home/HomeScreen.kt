package com.github.vitorfg8.blogapp.ui.home

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.vitorfg8.blogapp.R
import com.github.vitorfg8.blogapp.di.appModule
import com.github.vitorfg8.blogapp.ui.theme.BlogAppTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(), onPostClick: (
        title: String, description: String, date: String
    ) -> Unit, onAddPostClick: () -> Unit
) {

    viewModel.getPosts()
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    LaunchedEffect(uiState.showError) {
        if (uiState.showError) {
            snackbarHostState.showSnackbar(context.getString(R.string.failed_to_load))
        }
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(modifier = Modifier.shadow(4.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                titleContentColor = Color(0xFF32AAB8),
            ),
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.ExtraBold
                )
            })
    }, floatingActionButton = {
        FabNewPost {
            onAddPostClick()
        }
    }, snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
        ) {
            items(uiState.postList) { list ->
                PostItem(list) {
                    onPostClick(
                        it.title, it.description, it.date
                    )
                }
            }
        }
    }
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
private fun PostItem(
    homeUiState: HomePostUiState,
    onClick: (homeUiState: HomePostUiState) -> Unit
) {
    Card(modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 4.dp)
        .fillMaxWidth()
        .clickable {
            onClick(homeUiState)
        }) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = homeUiState.title, style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 18.sp, fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = homeUiState.date,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = homeUiState.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    BlogAppTheme {
        HomeScreen(onPostClick = { title, description, date -> }, onAddPostClick = {})
    }
}

@Preview
@Composable
private fun PostItemPreview(@PreviewParameter(LoremIpsum::class) text: String) {
    KoinApplication(application = {
        modules(appModule)
    }) {
        BlogAppTheme {
            PostItem(
                HomePostUiState(
                    "Hello World!", text, "31/05/2024 às 09:00"
                )
            ) {}
        }
    }
}