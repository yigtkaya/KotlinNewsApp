package com.kaya.newsapp.presentation.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kaya.newsapp.presentation.components.ArticleCard
import com.kaya.newsapp.presentation.components.TabBarRow
import com.kaya.newsapp.presentation.search.SearchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingView(
    viewModel: ArticlesViewModel = hiltViewModel()
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
            .fillMaxSize()
    ){
        TabBarRow(items = topList,
            selectedTab = viewModel.state.selectedTab,
            onSelectedIndexChange = {selectedTab ->
                viewModel.onEvent(ArticleEvent.onSelectedTabChange(selectedTab))
            }
        )
        SwipeRefresh(state = swipeRefreshState, onRefresh = {
            viewModel.onEvent(ArticleEvent.Refresh)
        }) {
            LazyColumn(modifier = Modifier.fillMaxSize(),content = {
                items(state.articles.size) { index ->
                    val article = state.articles[index]
                    ArticleCard(
                        article = article,
                        backgroundColor = if (index % 2 == 0) {
                            Color(0xFFEEBEBE)
                        } else {
                            Color(0xFF360505)
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .clickable {
                                // Navigate to the details screen
                            }
                    )
                }
            })
        }
    }

}