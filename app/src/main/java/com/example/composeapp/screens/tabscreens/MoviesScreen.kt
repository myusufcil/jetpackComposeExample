package com.example.composeapp.screens.tabscreens

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapp.ui.theme.Shapes

@ExperimentalAnimationApi
@Composable
fun MoviesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
    ) {
        IncreasedData()
        AnimationExtentBottom()
    }
}


@ExperimentalAnimationApi
@Composable
fun AnimationExtentBottom() {
    var visible by remember { mutableStateOf(true) }
    var size by remember { mutableStateOf(Size(100F, 100F)) }
    var rotate by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (rotate) 360F else 0F
    )
    Column {
        Row {
            Button(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .requiredWidth(128.dp),
                onClick = {
                    size =
                        if (size.height == 100F) Size(200F, 200F)
                        else Size(100F, 100F)
                }) {
                Text("Enlarge")
            }
            Button(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .requiredWidth(128.dp),
                onClick = {
                    rotate = !rotate
                }) {
                Text("Rotate")
                }
            Button(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .requiredWidth(128.dp),
                onClick = {
                    visible = !visible
                }) {
                Text("Visible")
            }
        }

        AnimatedVisibility(visible = visible,modifier= Modifier.rotate(rotationAngle)) {
            Box(
                modifier = Modifier
                    .padding(start = 48.dp, top = 24.dp)
                    .size(size.height.dp)
                    .background(Color.Blue)
            )
        }
    }
}

//Increased Data
@Preview(showBackground = true)
@ExperimentalAnimationApi
@Composable
fun IncreasedData() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        var count by remember { mutableStateOf(0) }
        Button(modifier = Modifier.padding(top = 12.dp, start = 24.dp), onClick = { count++ }) {
            Text("Add")
        }
        /*AnimatedContent(targetState = count) { targetCount ->
            // Make sure to use `targetCount`, not `count`.
            Text(text = "count: $targetCount")
        }*/
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (targetState > initialState) {
                    //esnek bir yapı
                    slideInHorizontally({ height -> height }) + fadeIn() with
                            slideOutVertically({ height -> -height }) + fadeOut()
                } else {
                    slideInHorizontally({ height -> -height }) + fadeIn() with
                            slideOutVertically({ height -> height }) + fadeOut()
                }.using(
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(text = "$targetCount", Modifier.padding(top = 24.dp, start = 24.dp))
        }
        Button(modifier = Modifier.padding(top = 12.dp, start = 24.dp), onClick = { count-- }) {
            Text("Remove")
        }
    }
}

/*@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    MoviesScreen()
}*/