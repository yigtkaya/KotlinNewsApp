package com.kaya.newsapp.presentation.bottomNavBar

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kaya.newsapp.presentation.navigation.Screens

@Composable
fun CustomBottomNavBar(
    items: List<Screens>,
    navController: NavController,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
            .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        items.forEach {
            CustomNavBarItem(
                item = it,
                isSelected = it.route == currentDestination?.route,
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        if (it.route == navController.currentDestination?.route) {
                            print("same route")
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}


@Composable
fun CustomNavBarItem(
    item: Screens,
    isSelected: Boolean,
    onClick : () -> Unit,
) {
    val backgroundColor = if (isSelected) Color.White else Color.Black

    Box(modifier = Modifier
        .clip(CircleShape)
        .padding(top = 16.dp)
        .background(backgroundColor)
        .clickable(onClick = onClick)) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

        ) {
                    Icon(imageVector = item.icon, contentDescription = null, tint = if (isSelected) Color.Blue else Color.White)
            }
        }
    }

