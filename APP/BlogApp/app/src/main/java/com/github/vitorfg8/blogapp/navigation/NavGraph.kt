package com.github.vitorfg8.blogapp.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.vitorfg8.blogapp.ui.addpost.AddPostScreen
import com.github.vitorfg8.blogapp.ui.home.HomeScreen
import com.github.vitorfg8.blogapp.ui.postdetails.PostDetailsScreen
import com.github.vitorfg8.blogapp.ui.postdetails.PostDetailsUiState


private const val ADD_POST_ROUTE = "addPost"
private const val HOME_ROUTE = "home"
private const val POST_DETAILS_ROUTE = "postDetails/{title}/{description}/{date}"

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HOME_ROUTE) {
        composable(HOME_ROUTE) {
            HomeScreen(onPostClick = { title, description, date ->
                val encodedTitle = Uri.encode(title)
                val encodedDescription = Uri.encode(description)
                val encodedDate = Uri.encode(date)
                navController.navigate("postDetails/$encodedTitle/$encodedDescription/$encodedDate")
            }, onAddPostClick = {
                navController.navigate(ADD_POST_ROUTE)
            })
        }
        composable(ADD_POST_ROUTE) {
            AddPostScreen(onBackPressed = {
                navController.navigateUp()
            }, onPostSaved = {
                navController.navigateUp()
            })
        }
        composable(
            route = POST_DETAILS_ROUTE,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("date") { type = NavType.StringType })
        ) { backStackEntry ->
            PostDetailsScreen(
                PostDetailsUiState(
                    title = backStackEntry.arguments?.getString("title").orEmpty(),
                    description = backStackEntry.arguments?.getString("description").orEmpty(),
                    date = backStackEntry.arguments?.getString("date").orEmpty()
                )
            ) {
                navController.navigateUp()
            }
        }
    }
}