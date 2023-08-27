package com.kaya.newsapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.kaya.newsapp.presentation.ArticleDetail.ArticleDetailView
import com.kaya.newsapp.presentation.landing.ArticlesViewModel
import com.kaya.newsapp.presentation.landing.LandingView
import com.kaya.newsapp.presentation.search.SearchView

sealed class Screens(val title: String, val route: String, val icon: ImageVector, ) {
    object Landing : Screens(title = "Landing", route = "landing", icon = Icons.Outlined.Home,)
    object Search : Screens(title = "Search", route = "search", icon = Icons.Outlined.Search,)

}

sealed class DetailScreens(val title: String, val route: String) {
    object ArticleDetail : DetailScreens(title = "Article Detail", route = "article_detail")
}

@Composable
fun BottomNavigationHost(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        route = Graph.BOTTOMBAR,
        startDestination = Screens.Landing.route,
        builder = {

        composable(Screens.Landing.route) {
            LandingView(
                onNavigateToDetail = {
                    navHostController.navigate(Graph.DETAIL) {

                    }
                },)
        }
        composable(Screens.Search.route) {
            SearchView(onNavigateToDetail = {
                navHostController.navigate(Graph.DETAIL)
            },)
        }
        detailsNavGraph(navHostController)
    })
}

fun NavGraphBuilder.detailsNavGraph(navController: NavController) {
        navigation(
            route = Graph.DETAIL,
            startDestination = DetailScreens.ArticleDetail.route
        ) {
            composable(DetailScreens.ArticleDetail.route,) {
                ArticleDetailView(
                    onClick = {
                        navController.popBackStack()
                    },
                )
            }
        }
}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController) : T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}


object Graph {
    const val BOTTOMBAR = "bottombar"
    const val DETAIL = "detail"
}