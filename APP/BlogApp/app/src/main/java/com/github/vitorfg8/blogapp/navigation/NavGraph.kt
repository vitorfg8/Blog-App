package com.github.vitorfg8.blogapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.vitorfg8.blogapp.AddPostScreen
import com.github.vitorfg8.blogapp.HomeScreen
import com.github.vitorfg8.blogapp.PostDetailsScreen


@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onPostClick = {

            }, onAddPostClick = {
                navController.navigate("addPost")
            })
        }
        composable("addPost") {
            AddPostScreen(
                onBackPressed = {
                    navController.navigateUp()
                },
                onPostSaved = {
                    navController.navigateUp()
                }
            )
        }
        composable("postDetails") {
            PostDetailsScreen()
        }
    }
}