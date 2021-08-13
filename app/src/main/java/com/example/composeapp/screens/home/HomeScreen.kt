package com.example.composeapp.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeapp.R

@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBar(
            title = { AppBarContent() },
            backgroundColor = colorResource(id = R.color.white),
            actions = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(painterResource(id =R.drawable.search), contentDescription = null)
                }
            }
        )
        val messageList = mutableListOf<Message>()
        var mapList = mutableMapOf<String, List<Message>>()
        for (i in 0 until 1) {
            messageList.add(
                Message(
                    stringResource(id = R.string.android),
                    stringResource(id = R.string.random_text)
                )
            )
        }
        mapList?.put("FAQ", messageList)
        mapList?.put("Jetpack Compose", messageList)
        mapList?.put("About Layouts", messageList)
        mapList?.put("Text", messageList)
        mapList?.put("List", messageList)
        mapList?.put("Animation", messageList)
        mapList?.put("Gestures", messageList)
        Conversation(mapList)
    }
}


@Composable
fun AppBarContent() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(id =R.string.app_name),
            color = colorResource(id = R.color.black)
        )
        Spacer(modifier = Modifier.padding(start = 24.dp))
        Icon(painter = painterResource(id = R.drawable.android), contentDescription = "android")
        Spacer(modifier = Modifier.padding(start = 24.dp))
        //Icon(painter = painterResource(id = R.drawable.search), contentDescription = "search")
    }
}


@Composable
fun MessageCard(msg: List<Message>) {
    msg.forEach{_msg->
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.android),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            var isExpanded by remember { mutableStateOf(false) }
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = _msg.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                ) {
                    Text(
                        text = _msg.body,
                        modifier = Modifier
                            .padding(all = 4.dp)
                            .fillMaxWidth(),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }

}

@Composable
fun Header(string: String) {
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.trabzon_siyahi))
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = "$string",
            color = colorResource(id = R.color.white),
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun Conversation(grouped: Map<String, List<Message>>?) {
    LazyColumn {
        grouped?.forEach { (initial, contactsForInitial) ->
            stickyHeader {
                Header(initial)
            }
            items(1) { message ->
                grouped?.values?.forEach {
                    MessageCard(it)
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

/*
*
*  Button(
                onClick = { /*asd*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.yellow)),
                //border = BorderStroke(3.dp,color = colorResource(id = R.color.black)),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "Giriş Yap",
                    color = colorResource(id = R.color.black)
                )
            }
            Button(
                onClick = { /*asd*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier =
                Modifier
                    .padding(start = 12.dp),
                //border = BorderStroke(3.dp,color = colorResource(id = R.color.black))
                shape = MaterialTheme.shapes.small
            )
            {
                Text(
                    text = "Üye Ol",
                    color = colorResource(id = R.color.black)
                )
            }*/