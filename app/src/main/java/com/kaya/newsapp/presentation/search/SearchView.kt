package com.kaya.newsapp.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kaya.newsapp.presentation.landing.ArticleEvent
import com.kaya.newsapp.presentation.components.ArticleCard
import com.kaya.newsapp.presentation.landing.ArticlesViewModel
import com.kaya.newsapp.presentation.theme.paleBlue
import com.kaya.newsapp.presentation.theme.paleGreen
import com.kaya.newsapp.presentation.theme.paleRed
import com.kaya.newsapp.presentation.theme.paleYellow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView (
    viewModel: SearchViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val colors = listOf(
        paleRed,
        paleGreen,
        paleYellow,
        paleBlue
    )
    Column (
        modifier = Modifier
            .fillMaxSize().background(Color.Black)
    ){
        OutlinedTextField(

            value = state.searchQuery,
            onValueChange =  {
                viewModel.onEvent(SearchEvent.onSearchQueryChange(it))
            },
            modifier = Modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search", color = Color.White)
            },
            maxLines = 1,
            singleLine = true,
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize().padding(16.dp),
            content = {
            items(state.articles.size) { index ->
                val article = state.articles[index]
                val randomColor = colors.random()
                ArticleCard(
                    article = article,
                    backgroundColor = randomColor,
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