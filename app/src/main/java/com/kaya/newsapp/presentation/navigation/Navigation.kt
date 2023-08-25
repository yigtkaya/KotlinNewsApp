package com.kaya.newsapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kaya.newsapp.R
import com.kaya.newsapp.presentation.landing.LandingView
import com.kaya.newsapp.presentation.landing.SearchView

sealed class Screens(val title: String, val route: String, val icon: ImageVector, ) {
    object Landing : Screens(title = "Landing", route = "landing", icon = Icons.Outlined.Home,)
    object Search : Screens(title = "Search", route = "search", icon = Icons.Outlined.Search,)

}

@Composable
fun BottomNavigationHost(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = Screens.Landing.route, builder = {
        composable(Screens.Landing.route) {
            LandingView()
        }
        composable(Screens.Search.route) {
            SearchView()
        }
    })
}
