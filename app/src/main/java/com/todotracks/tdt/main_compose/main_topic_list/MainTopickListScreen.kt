package com.todotracks.tdt.main_compose.main_topic_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun mainTopicListScreen(navController: NavController,){
    val fakeMainTopicList = run{
        val list = mutableListOf<String>()
        for(i in 1..100){
            list.add(i.toString())
        }
        list
    }
    Scaffold(
        topBar = { TopAppBar() {

        }}
    ) {
        LazyColumn{
            items(fakeMainTopicList){ text ->
                Text(text = text)
            }
        }
    }
}