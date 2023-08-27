package com.kaya.newsapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaya.newsapp.domain.models.Article
import com.kaya.newsapp.presentation.theme.paleBlue
import com.kaya.newsapp.presentation.theme.paleGreen
import com.kaya.newsapp.presentation.theme.paleRed
import com.kaya.newsapp.presentation.theme.paleYellow

@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit
) {

    val color = listOf(
        paleBlue,
        paleYellow,
        paleGreen,
        paleRed
    ).random()

        Box(modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(color = color)
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .clickable {
                onClick()
            }
        ) {

            Column (
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = article.title ?: "Unknown Title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    softWrap = true,
                    maxLines = 3,
                    lineHeight = 40.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 12.dp, end = 20.dp),
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = article.author ?: "Unknown Author",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    softWrap = true,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 12.dp, end = 20.dp),
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = article.description ?: "Unknown Description",
                    fontWeight = FontWeight.W600,
                    fontSize = 14.sp,
                    softWrap = true,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 12.dp, end = 20.dp),
                    )
            }
        }
}
