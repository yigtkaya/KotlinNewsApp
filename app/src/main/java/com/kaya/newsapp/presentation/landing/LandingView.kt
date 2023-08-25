@file:OptIn(ExperimentalMaterial3Api::class)

package com.kaya.newsapp.presentation.landing

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kaya.newsapp.presentation.ArticleCard
import com.kaya.newsapp.presentation.ArticleEvent
import com.kaya.newsapp.presentation.ArticlesViewModel
import com.kaya.newsapp.presentation.components.TabBarRow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

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
        OutlinedTextField(value = state.searchQuery,
            onValueChange =  {
                viewModel.onEvent(ArticleEvent.onSearchQueryChange(it))
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search", color = Color.White)
            },
            maxLines = 1,
            singleLine = true,
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
                            androidx.compose.ui.graphics.Color(0xFFEEBEBE)
                        } else {
                            androidx.compose.ui.graphics.Color(0xFF360505)
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