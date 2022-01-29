package com.todotracks.tdt.main_compose.main_topic_added

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.todotracks.tdt.main_compose.common.AppTopBar
import com.todotracks.tdt.main_compose.common.DatePickerview
import com.todotracks.tdt.main_compose.common.Event
import com.todotracks.tdt.main_compose.common.Screens
import com.todotracks.tdt.model.MainTopic
import com.todotracks.tdt.ui.theme.Gray
import com.todotracks.tdt.ui.theme.GrayLight
import com.todotracks.tdt.ui.theme.Indigo
import com.todotracks.tdt.ui.theme.Orenge

@Composable
fun mainTopicAddedScreen(
    navController: NavController,
    vm: MainTopicAddViewModel = viewModel()
) {
    val context = LocalContext.current
    var title by remember {
        mutableStateOf("")
    }
    val (startDateState, startDatePicker) = DatePickerview()
    val (endDateState, endDatePicker) = DatePickerview()
    var startDate by remember {
        startDateState
    }
    var endDate by remember {
        endDateState
    }

    if(vm.added.value) {
        navController.popBackStack()
        vm.setAdded(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Indigo)
    ) {
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
                text = "TITLE",
                color = Color.White,
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(start = 17.dp)
                    .align(Alignment.CenterStart),
                fontWeight = FontWeight.Bold
            )

        }
        Column(
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
                .padding(horizontal = 35.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White),
                value = title,
                onValueChange = { title = it },
                label = { Text("제목을 입력해주세요.") }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "시작일")
            Spacer(modifier = Modifier.height(3.dp))
            datePickerTextView(text = startDate, datePicker = startDatePicker)
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "종료")
            Spacer(modifier = Modifier.height(3.dp))
            datePickerTextView(text = endDate, datePicker = endDatePicker)
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    if (title.isNotBlank() && startDate.isNotBlank() && endDate.isNotBlank()) {
                        vm.addTopic(
                            MainTopic(
                                end_date = endDate,
                                start_date = startDate,
                                title = title
                            )
                        )
                    }
                },
                modifier =
                Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (title.isNotBlank() && startDate.isNotBlank() && endDate.isNotBlank()) {
                        Orenge
                    } else {
                        Gray
                    }
                )
            ) {
                Text(text = "여행 추가")
            }
        }
    }
}


@Composable
fun datePickerTextView(text: String, datePicker: DatePickerDialog) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(GrayLight)
            .clickable {
                datePicker.show()
            },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.padding(start = 7.dp),
                text = if (text.isBlank()) "날짜를 선택해 주세요." else text,
                color = Gray,
            )
        }
    }

}

