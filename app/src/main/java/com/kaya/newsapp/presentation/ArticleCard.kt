package com.kaya.newsapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

@Composable
fun ArticleCard(
    article: Article,
    backgroundColor: Color,
    modifier : Modifier = Modifier,
) {

        Box(modifier = Modifier
            .background(color = backgroundColor)
            .clip(shape = RoundedCornerShape(16.dp))) {

            Column {
                Text(
                    text = article.title ?: "Unknown",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ){
                    Text(
                        text = article.author ?: "Unknown",
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Button(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(30.dp))
                            .background(Color.Black),
                        onClick = {
                            // show toast message that this function is not working right now

                        }) {
                        Text(text = "Follow", textAlign = TextAlign.Center, color = Color.White, fontSize = 14.sp)
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = article.description ?: "Unknown",
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
}
