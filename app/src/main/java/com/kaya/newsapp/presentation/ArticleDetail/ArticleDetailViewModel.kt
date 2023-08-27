package com.kaya.newsapp.presentation.ArticleDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.kaya.newsapp.data.repository.NewsRepositoryImpl
import com.kaya.newsapp.presentation.bottomNavBar.BottomBarState
import com.kaya.newsapp.presentation.theme.paleBlue
import com.kaya.newsapp.presentation.theme.paleGreen
import com.kaya.newsapp.presentation.theme.paleRed
import com.kaya.newsapp.presentation.theme.paleYellow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val repository: NewsRepositoryImpl
): ViewModel() {

    var bottomBarState by mutableStateOf(BottomBarState())
    var color : Color = paleBlue
    init {
        bottomBarState = bottomBarState.copy(shouldShowBottomBar = false)

        color = listOf(
            paleBlue,
            paleYellow,
            paleGreen,
            paleRed
        ).random()
    }

}