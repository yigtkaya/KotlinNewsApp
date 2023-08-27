package com.kaya.newsapp.presentation.ArticleDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.kaya.newsapp.domain.models.Article
import com.kaya.newsapp.presentation.theme.paleBlue
import com.kaya.newsapp.presentation.theme.paleGreen
import com.kaya.newsapp.presentation.theme.paleRed
import com.kaya.newsapp.presentation.theme.paleYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailView(
    viewModel: ArticleDetailViewModel = hiltViewModel(),
    onClick: () -> Unit
) {

    val color = listOf(
        paleBlue,
        paleYellow,
        paleGreen,
        paleRed
    ).random()


    Scaffold (

    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color)
        ) {

            IconButton(
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color.LightGray),
                onClick = {
                    onClick()
                }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }
    }

}