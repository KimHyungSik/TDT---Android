package com.todotracks.tdt.main_compose.sub_topic_list

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.todotracks.tdt.dto.MainTopicDto
import com.todotracks.tdt.dto.SubTopicDto
import com.todotracks.tdt.dto.SubTopicsDto
import com.todotracks.tdt.main_compose.common.Screens
import com.todotracks.tdt.main_compose.main_topic_list.MainTopicDateList
import com.todotracks.tdt.src.check_map.MapCheckActivity
import com.todotracks.tdt.ui.theme.*

@Composable
fun subTopicScreen(
    navController: NavController,
    mainTopicId: Int?,
    date: String?,
    mainTopicTitle: String?,
    vm: SubTopicListViewModel = viewModel()
) {

    Box(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Indigo)
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f),
                ) {
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(5.dp),
                        onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "ArrowBack",
                            tint = Color.White
                        )
                    }

                    Text(
                        text = mainTopicTitle ?: "TITLE",
                        color = Color.White,
                        fontSize = 25.sp,
                        modifier = Modifier
                            .padding(start = 17.dp)
                            .align(Alignment.CenterStart),
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                        .background(Color.White)
                        .padding(top = 75.dp)
                ) {
                    LazyColumn {
                        itemsIndexed(vm.subTopics.value.sub_topics) { index, subTopic ->
                            SubTopicItem(subTopic, index, navController)
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 100.dp)
                .padding(horizontal = 50.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            GreenLight,
                            GreenDark
                        ),
                        start = Offset.Zero,
                        end = Offset.Infinite
                    )
                )
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = vm.completeRate.value.toString() + "%",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun SubTopicItem(subTopicDto: SubTopicDto, index: Int, navHostController: NavController) {

    val context = LocalContext.current as Activity
    Column(
        modifier = Modifier.clickable {
            val intent = Intent(context, MapCheckActivity::class.java)
            intent.putExtra("latitude", subTopicDto.latitude)
            intent.putExtra("longitude", subTopicDto.longitude)
            context.startActivity(intent)
        }
    )
    {
        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 22.dp),
                text = "${index + 1}",
                textAlign = TextAlign.Center,
                color = Gray,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )

            Column(
                modifier = Modifier
                    .padding(start = 22.dp)
                    .fillMaxWidth(0.8f)
            ) {
                Text(
                    text = subTopicDto.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (subTopicDto.is_complete) GrayLight else Color.Black
                )
            }

            IconButton(
                onClick = {
                },
            ) {
                Icon(
                    Icons.Filled.ArrowForward,
                    contentDescription = "add topic",
                    tint = Color.Gray,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}