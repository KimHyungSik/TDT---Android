package com.todotracks.tdt.main_compose.main_topic_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.todotracks.tdt.dto.MainTopic
import com.todotracks.tdt.ui.theme.GrayLight
import com.todotracks.tdt.ui.theme.Indigo

@Composable
fun mainTopicListScreen(
    navController: NavController,
    vm: MainTopicListViewModel = viewModel()
) {
    val fakeMainTopicList = vm.mainTopicList.value
    Scaffold(
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Indigo)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "TITLE",
                    color = Color.White,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 17.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Card(
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                Column() {
                    Text(
                        text = "TITLE",
                        fontSize = 23.sp,
                        modifier = Modifier.padding(start = 17.dp, top = 20.dp),
                        fontWeight = FontWeight.Bold
                    )
                    LazyColumn(
                        modifier = Modifier.padding(bottom = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(17.dp),
                    ) {
                        itemsIndexed(fakeMainTopicList) { index, mainTopic ->
                            MainTopicItem(mainTopic, index, navController)
                        }
                    }
                }
            }
        }
    }
}