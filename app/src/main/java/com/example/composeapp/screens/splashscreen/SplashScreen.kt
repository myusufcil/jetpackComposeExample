package com.example.composeapp.screens.splashscreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeapp.R
import com.example.composeapp.screens.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember{
        Animatable(0F)
    }
    LaunchedEffect(key1 =true){
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate(Screens.HomeScreen.route)
    }
    Box(contentAlignment = Alignment.Center,modifier =Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.ic_splash_icon), contentDescription ="Logo",
        modifier = Modifier.scale(scale.value).size(200.dp))
    }
}