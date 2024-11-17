package com.example.newsapp.util

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.presentation.articleScreen.ArticlesScreen
import com.example.newsapp.presentation.news_screen.NewsScreen
import com.example.newsapp.presentation.news_screen.NewsViewModel

@Composable
fun NavGraphSetup(
    navHostController: NavHostController
) {
    val viewModel: NewsViewModel = hiltViewModel()
    NavHost(
        navController = navHostController,
        startDestination = "home_screen"
    ) {
        composable(route = "home_screen") {
            NewsScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                onReadFullStoryButtonClick = {url->
                    navHostController.navigate("articles_screen?web_url=$url")
                }
            )
        }
        composable(
            route = "articles_screen?web_url={web_url}",
            arguments = listOf(navArgument(name = "web_url") {
                type = NavType.StringType
            })
        ) {backStackEntry->
            ArticlesScreen(
                url = backStackEntry.arguments?.getString("web_url"),
                onBackPressed = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}