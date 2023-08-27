@file:OptIn(ExperimentalMaterial3Api::class)

package com.kaya.newsapp.presentation

import android.os.Bundle
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.kaya.newsapp.presentation.landing.ArticlesViewModel
import com.kaya.newsapp.presentation.bottomNavBar.CustomBottomNavBar
import com.kaya.newsapp.presentation.components.ToolBar
import com.kaya.newsapp.presentation.navigation.BottomNavigationHost
import com.kaya.newsapp.presentation.navigation.Screens
import com.kaya.newsapp.presentation.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ArticlesViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
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
                        floatingActionButtonPosition = FabPosition.Center,
                        floatingActionButton = {
                            CustomBottomNavBar(items = bottomList, navController = navController)
                        },
                        topBar = { ToolBar() },
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

@Composable
fun MainScaffold(
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {},
) {
    Scaffold(
        topBar = topBar,
        bottomBar = bottomBar,
        content = content,
    )
}