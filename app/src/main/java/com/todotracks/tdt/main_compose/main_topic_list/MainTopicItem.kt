package com.todotracks.tdt.main_compose.main_topic_list

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.todotracks.tdt.dto.MainTopicDto
import com.todotracks.tdt.main_compose.common.Screens
import com.todotracks.tdt.ui.theme.Gray
import com.todotracks.tdt.ui.theme.GrayLight

@Composable
fun MainTopicItem(mainTopicDto: MainTopicDto, index: Int, navHostController: NavController) {
    var show by remember {
        mutableStateOf(false)
    }

    val showF: State<Float>
    Column(
        modifier = Modifier.animateContentSize()
    ) {
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
                Text(text = mainTopicDto.start_date, color = Gray)
                Text(text = mainTopicDto.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            IconButton(
                onClick = {
                    navHostController.navigate(Screens.SubTopicAddedScreen.url + "/${mainTopicDto.main_no}/${mainTopicDto.title}")
                },
            ) {
                Icon(
                    Icons.Filled.AddCircle,
                    contentDescription = "add topic",
                    tint = Color.Gray,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        if (!mainTopicDto.date_list.isNullOrEmpty()) {
            if (show) {
                MainTopicDateList(
                    mainTopicDto.date_list,
                    navHostController,
                    mainTopicDto.main_no,
                    mainTopicDto.title
                )
            }
        }
    }
}

@Composable
fun MainTopicDateList(
    dateList: List<String>,
    navController: NavController,
    mainTopicId: Int,
    mainTopicTitle: String
) {
    var visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    Column(

    ) {
        dateList.forEachIndexed { index, s ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screens.SubTopicListScreen.url + "/$mainTopicId/$s/$mainTopicTitle") {
                            popUpTo(Screens.MainTopicListScreen.url) {
                                inclusive = true
                            }
                        }
                    }
                    .padding(vertical = 8.dp)
            ) {
                Spacer(modifier = Modifier.width(23.dp))
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "arrowforward",
                    tint = GrayLight
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = s
                )
            }
            if (index < dateList.size - 1)
                Divider(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = GrayLight,
                    thickness = 1.dp
                )
        }
    }

}