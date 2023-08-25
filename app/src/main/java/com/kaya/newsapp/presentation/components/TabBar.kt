package com.kaya.newsapp.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
        .padding(8.dp)
        .height(IntrinsicSize.Min)
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
                color = if (isSelected) Color.Red else Color.White
            )
    }
}


@Composable
fun TabBarRow(
    items: List<String>,
    selectedTab: String,
    onSelectedIndexChange: (String) -> Unit
) {

    Box {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ){
            items.forEach { item ->
                TabBarItem(
                    title = item,
                    isSelected = item == selectedTab,
                    onClick = {
                        onSelectedIndexChange(item)
                    }
                )
            }
        }
    }
}
