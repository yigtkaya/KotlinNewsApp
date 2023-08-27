package com.kaya.newsapp.presentation.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.gson.Gson
import com.kaya.newsapp.presentation.components.ArticleCard
import com.kaya.newsapp.presentation.components.TabBarRow
import com.kaya.newsapp.presentation.navigation.DetailScreens
import com.kaya.newsapp.presentation.theme.paleBlue
import com.kaya.newsapp.presentation.theme.paleGreen
import com.kaya.newsapp.presentation.theme.paleRed
import com.kaya.newsapp.presentation.theme.paleYellow

@Composable
fun LandingView(
    onNavigateToDetail: () -> Unit,
    navController: NavHostController,
    viewModel: ArticlesViewModel = hiltViewModel(),
) {

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state

    val topList = listOf(
        "Trending",
        "Health",
        "Sports",
        "Finance"
    )

    Column (
        modifier = Modifier
            .fillMaxSize().background(Color.Black)
    ){
        TabBarRow(items = topList,
            selectedTab = viewModel.state.selectedTab,
            onSelectedIndexChange = {selectedTab ->
                viewModel.onEvent(ArticleEvent.onSelectedTabChange(selectedTab))
            }
        )
        SwipeRefresh(
            modifier = Modifier.padding(horizontal = 16.dp),
            state = swipeRefreshState, onRefresh = {
            viewModel.onEvent(ArticleEvent.Refresh)
        }) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize(),content = {
                items(state.articles.size) { index ->
                    val article = state.articles[index]
                    ArticleCard(
                        article = article,
                           onClick = {
                               navController.currentBackStackEntry?.savedStateHandle?.set(
                                   key = "article",
                                   value = article)
                               navController.navigate(DetailScreens.ArticleDetail.route)
                           }
                    )
                }
            })
        }
    }

}