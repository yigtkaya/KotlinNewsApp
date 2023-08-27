package com.kaya.newsapp.presentation.ArticleDetail

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.kaya.newsapp.domain.models.Article

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticleDetailView(
    viewModel: ArticleDetailViewModel = hiltViewModel(),
    article: Article?,
    onClick: () -> Unit
) {
    var title = ""
    var description = ""
    var url = ""
    var publishedAt = ""
    var content = ""
    var author = ""
    var imageUrl = ""


    article?.let {
        title = it.title ?: "Unknown Title"
        description = it.description ?: "Unknown Description"
        url = it.url ?: "Unknown URL"
        publishedAt = it.publishedAt ?: "Unknown Published At"
        content = it.content ?: "Unknown Content"
        author = it.author ?: "Unknown Author"
        imageUrl = it.imageUrl ?: "Unknown Image URL"
    }

    val context = LocalContext.current
    val backgroundColor = viewModel.color

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(backgroundColor)
                .padding(start = 12.dp, end = 20.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.LightGray.copy(alpha = 0.3f)),
                onClick = {
                    onClick()
                }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                softWrap = true,
                lineHeight = 40.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW,)
                        intent.data = Uri.parse(url)
                        startActivity(context, intent, null)
                },
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = author,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                softWrap = true,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier,
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = description,
                fontWeight = FontWeight.W600,
                fontSize = 14.sp,
                softWrap = true,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier,
            )
            Spacer(modifier = Modifier.height(24.dp))

            GlideImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW,)
                        intent.data = Uri.parse(imageUrl)
                        startActivity(context, intent, null)
                    }
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(24.dp)),
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = content,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                softWrap = true,
                maxLines = 24,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 6.dp, end = 6.dp),
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
}