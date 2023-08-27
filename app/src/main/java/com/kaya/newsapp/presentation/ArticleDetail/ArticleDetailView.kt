package com.kaya.newsapp.presentation.ArticleDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ArticleDetailView(
    viewModel: ArticleDetailViewModel = hiltViewModel(),
    onClick: () -> Unit
) {

    val backgroundColor = viewModel.color

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {

            IconButton(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
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