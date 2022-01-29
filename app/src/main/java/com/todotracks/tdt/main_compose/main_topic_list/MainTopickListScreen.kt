package com.todotracks.tdt.main_compose.main_topic_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.todotracks.tdt.dto.MainTopic

@Composable
fun mainTopicListScreen(
    navController: NavController,
    viewModel: MainTopicListViewModel = viewModel()
    ){
    val fakeMainTopicList = run{
        val list = mutableListOf<MainTopic>()
        for(i in 1..100){
            list.add(
                MainTopic(
                    date_list = listOf("2022-01-0$i"),
                    end_date = "2023-01-$i",
                    start_date = "2022-01-$i",
                    main_no = i,
                    title = "TITLE$i"
                )
            )
        }
        list
    }
    Scaffold(
    ) {
        LazyColumn{
            items(fakeMainTopicList){ mainTopic ->
                MainTopicItem(mainTopic)
            }
        }
    }
}

@Composable
fun MainTopicItem(mainTopic : MainTopic){
    Column() {
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = mainTopic.title)
    }
}