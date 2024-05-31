package com.github.vitorfg8.blogapp.ui.addpost

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.vitorfg8.blogapp.R
import com.github.vitorfg8.blogapp.di.appModule
import com.github.vitorfg8.blogapp.ui.theme.BlogAppTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostScreen(
    viewModel: AddPostViewModel = koinViewModel(),
    onBackPressed: () -> Unit,
    onPostSaved: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    LaunchedEffect(uiState.showError) {
        if (uiState.showError) {
            snackbarHostState.showSnackbar(context.getString(R.string.failed_to_salve_post))
            viewModel.resetErrorState()
        }
    }

    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.shadow(4.dp), title = {
            Text(text = stringResource(R.string.add_post))
        }, navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        })
    }, snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { padding ->
        Column(
            Modifier.padding(padding)
        ) {
            TextField(modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                value = uiState.postTitle,
                placeholder = {
                    Text(
                        text = stringResource(R.string.enter_a_title),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                onValueChange = { viewModel.updateTitle(it) })
            TextField(modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                value = uiState.postDescription,
                placeholder = {
                    Text(
                        text = stringResource(R.string.add_post_text),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                onValueChange = { viewModel.updateDescription(it) })
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                enabled = uiState.isButtonEnabled,
                onClick = {
                    viewModel.savePost()
                },
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = stringResource(id = R.string.add_post))
            }
            if (uiState.isPostSaved) {
                onPostSaved()
            }
        }
    }
}

@Preview
@Composable
private fun AddPostScreenPreview() {

    KoinApplication(application = {
        modules(appModule)
    }) {
        BlogAppTheme {
            AddPostScreen(onBackPressed = {}, onPostSaved = {})
        }
    }
}