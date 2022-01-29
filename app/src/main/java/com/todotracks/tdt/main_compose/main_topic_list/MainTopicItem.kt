package com.todotracks.tdt.main_compose.main_topic_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.todotracks.tdt.dto.MainTopic
import com.todotracks.tdt.main_compose.common.Screens
import com.todotracks.tdt.ui.theme.GrayLight

@Composable
fun MainTopicItem(mainTopic: MainTopic, index: Int, navHostController: NavController) {
    var show by remember {
        mutableStateOf(false)
    }
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .clickable {
                    show = !show
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 22.dp),
                text = "$index",
                textAlign = TextAlign.Center,
                color = GrayLight,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )

            Column(
                modifier = Modifier
                    .padding(start = 22.dp)
                    .fillMaxWidth(0.8f)
            ) {
                Text(text = mainTopic.start_date, color = GrayLight)
                Text(text = mainTopic.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    Icons.Filled.AddCircle,
                    contentDescription = "add topic",
                    tint = Color.Gray,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        if (!mainTopic.date_list.isNullOrEmpty()) {
            if (show) {
                for (n in mainTopic.date_list) {
                    MainTopicDateList(n, navHostController)
                }
            }
        }
    }
}

@Composable
fun MainTopicDateList(
    n: String, navHostController:
    NavController
) {

    Text(modifier = Modifier.clickable { navHostController.navigate(Screens.MainTopicAddedScreen.url) },text = n)
}