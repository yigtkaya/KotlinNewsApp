package com.kaya.newsapp.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TabBarItem (
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
){

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            onClick()
        }
    ) {

        // double the text size on selection
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = 0.4f,
                            stiffness = 500f
                        )
                    ),
                text = title,
                color = Color.White
            )
    }
}


@Composable
fun TabBarRow(
    items: List<String>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit
) {

    val selectedTitle = items[selectedIndex]

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {

        items.forEachIndexed { index, title ->
            TabBarItem(
                title = title,
                isSelected = title == selectedTitle,
                onClick = { onSelectedIndexChange(index) }
            )
        }
    }
}
