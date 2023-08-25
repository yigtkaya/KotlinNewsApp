@file:OptIn(ExperimentalMaterial3Api::class)

package com.kaya.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.kaya.newsapp.presentation.ArticleEvent
import com.kaya.newsapp.presentation.ArticlesViewModel
import com.kaya.newsapp.presentation.bottomNavBar.CustomBottomNavBar
import com.kaya.newsapp.presentation.components.TabBarRow
import com.kaya.newsapp.presentation.navigation.BottomNavigationHost
import com.kaya.newsapp.presentation.navigation.Screens
import com.kaya.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ArticlesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bottomList = listOf(
                Screens.Landing,
                Screens.Search
            )
            val navController = rememberNavController()
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
                    Scaffold (
                        bottomBar = {
                            CustomBottomNavBar(items = bottomList, navController = navController)
                        },
                        content = {
                            Box(modifier = Modifier.padding(it)) {
                                BottomNavigationHost(navHostController = navController)
                            }
                        }
                    )
                }
            }
        }
    }
}
