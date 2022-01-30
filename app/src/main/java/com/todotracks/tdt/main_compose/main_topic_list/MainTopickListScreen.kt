package com.todotracks.tdt.main_compose.main_topic_list

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.todotracks.tdt.main_compose.main_topic_added.mainTopicAddItem
import com.todotracks.tdt.ui.theme.Indigo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun mainTopicListScreen(
    navController: NavController,
    vm: MainTopicListViewModel = viewModel()
) {

    var first by remember {
        mutableStateOf(true)
    }
    var state by remember {
        mutableStateOf(1)
    }

    Scaffold(
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Indigo)
                .animateContentSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "여행하세요",
                    color = Color.White,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 17.dp),
                    fontWeight = FontWeight.Bold
                )
                Button(
                    modifier = Modifier.padding(end = 15.dp),
                    onClick = {
                        if (state == 1){
                            state = 0
                            CoroutineScope(Dispatchers.Main).launch {
                                delay(500)
                                state = 2
                            }
                        }
                        if (state == 2){
                            state = 0
                            CoroutineScope(Dispatchers.Main).launch {
                                delay(500)
                                state = 1
                            }
                        }
                    }) {
                    Row {
                        if(state==1) Icon(Icons.Default.AddCircle, contentDescription = "Added mainTopick")
                        else Icon(Icons.Default.List, contentDescription = "Added mainTopick")
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(text = if(state==1) "여행 추가" else "여행 보기")
                    }
                }
            }
            Card(
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .animateContentSize()
                ) {
                    when (state) {
                        1 -> mainTopicList(vm, navController)
                        2 -> mainTopicAddItem(navController, added = {
                            state = 0
                            CoroutineScope(Dispatchers.Main).launch {
                                vm.getMainTopicList()
                                delay(500)
                                state = 1
                            }
                        })
                    }
                }
            }
        }
    }
}


@Composable
fun mainTopicList(
    vm: MainTopicListViewModel,
    navController: NavController,
) {
    var isRefreshing by remember { mutableStateOf(false) }
    val mainTopicList = vm.mainTopicDtoList.value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    Text(
        text = "계획 중인 여행",
        fontSize = 23.sp,
        modifier = Modifier.padding(start = 17.dp, top = 20.dp),
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace
    )
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { vm.getMainTopicList() },
    ) {
        LazyColumn(
            modifier = Modifier.padding(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(17.dp),
        ) {
            itemsIndexed(mainTopicList) { index, mainTopic ->
                MainTopicItem(mainTopic, index, navController)
            }
        }
    }
}
