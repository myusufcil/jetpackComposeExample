package com.example.composeapp.screens

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composeapp.MainViewModel
import com.example.composeapp.R
import com.example.composeapp.screens.tabscreens.BooksScreen
import com.example.composeapp.screens.tabscreens.MoviesScreen
import com.example.composeapp.screens.tabscreens.MusicScreen

sealed class Screens(val route:String, @StringRes val resourceId:Int,val icon:ImageVector){
    object HomeScreen: Screens(route ="home", R.string.main_screen, Icons.Default.Home)
    object Live: Screens(route ="Live", R.string.live,Icons.Default.AccountCircle)
    object SplashScreen: Screens(route ="splash_screen", R.string.splash_screen,Icons.Default.AccountCircle)
}

sealed class TabScreen(var icon: Int, var title: String, var screen: @Composable () -> Unit) {
    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    data class Music(val viewModel:MainViewModel?) : TabScreen(R.drawable.ic_music, "Muzik", { MusicScreen(viewModel!!) })
    @ExperimentalAnimationApi
    object Movies : TabScreen(R.drawable.ic_movie, "Filmler", { MoviesScreen() })
    object Books : TabScreen(R.drawable.ic_book, "Kitaplar", { BooksScreen() })
}