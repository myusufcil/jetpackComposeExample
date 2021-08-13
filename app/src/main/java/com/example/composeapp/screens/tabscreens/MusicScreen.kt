package com.example.composeapp.screens.tabscreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.composeapp.MainViewModel
import com.example.composeapp.response.Track

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun MusicScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        viewModel.recommendations?.value?.tracks?.let { _tracks -> MusicComponent(_tracks) }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun MusicComponent(tracks: List<Track>) {

    var visible by remember { mutableStateOf(true) }
    LazyVerticalGrid(
        //burayı anlat, anlık değişim
        cells = GridCells.Fixed(3)
    ) {
        tracks.mapIndexedNotNull { index, _track ->
            items(1) {
                Column(
                    //burada sıralama önemli
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 12.dp, bottom = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = _track.subtitle, modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                            .clickable {
                                visible = !visible
                            },
                        textAlign = TextAlign.Center
                    )
                    AnimatedVisibility(visible = visible) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = rememberImagePainter(_track.images.coverart),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(128.dp)
                                    .clip(CircleShape)                       // clip to the circle shape
                                    .border(2.dp, Color.Gray, CircleShape)
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                            )
                            Text(
                                text = _track.title, modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(top = 8.dp, bottom = 4.dp),
                                textAlign = TextAlign.Center
                            )
                            Divider(
                                color = Color.Black,
                                thickness = 1.dp,
                                modifier = Modifier
                                    .padding(start = 12.dp, end = 12.dp)
                                    .fillMaxWidth()
                                    .fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}
