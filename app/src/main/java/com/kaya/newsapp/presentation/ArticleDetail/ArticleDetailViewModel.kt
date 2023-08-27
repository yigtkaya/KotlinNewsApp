package com.kaya.newsapp.presentation.ArticleDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.kaya.newsapp.data.repository.NewsRepositoryImpl
import com.kaya.newsapp.presentation.bottomNavBar.BottomBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val repository: NewsRepositoryImpl
): ViewModel() {

    var bottomBarState by mutableStateOf(BottomBarState())
    init {
        bottomBarState = bottomBarState.copy(shouldShowBottomBar = false)
    }

}