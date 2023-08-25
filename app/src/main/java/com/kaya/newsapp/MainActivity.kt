@file:OptIn(ExperimentalMaterial3Api::class)

package com.kaya.newsapp

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Up
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.kaya.newsapp.presentation.bottomNavBar.CustomBottomNavBar
import com.kaya.newsapp.presentation.components.TabBarRow
import com.kaya.newsapp.presentation.landing.LandingView
import com.kaya.newsapp.presentation.landing.SearchView
import com.kaya.newsapp.presentation.navigation.BottomNavigationHost
import com.kaya.newsapp.presentation.navigation.Screens
import com.kaya.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bottomList = listOf(
                Screens.Landing,
                Screens.Search
            )
            val topList = listOf(
                "Trending",
                "Health",
                "Sports",
                "Finance"
            )

            val navController = rememberNavController()
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
                    Scaffold (
                        bottomBar = {
                            CustomBottomNavBar(items = bottomList, navController = navController)
                        }
                    ){
                        TabBarRow(items = topList, selectedIndex = 0, onSelectedIndexChange = )
                        Box(modifier = Modifier.padding(it)) {
                            BottomNavigationHost(navHostController = navController)
                        }
                    }
                }
            }
        }
    }
}
