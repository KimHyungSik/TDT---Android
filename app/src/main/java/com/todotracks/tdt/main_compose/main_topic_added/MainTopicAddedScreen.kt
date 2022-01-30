package com.todotracks.tdt.main_compose.main_topic_added

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.todotracks.tdt.main_compose.common.DatePickerview
import com.todotracks.tdt.main_compose.common.Screens
import com.todotracks.tdt.main_compose.component.datePickerTextView
import com.todotracks.tdt.model.MainTopic
import com.todotracks.tdt.ui.theme.Gray
import com.todotracks.tdt.ui.theme.Indigo
import com.todotracks.tdt.ui.theme.Orenge

@ExperimentalComposeUiApi
@Composable
fun mainTopicAddedScreen(
    navController: NavController,
    vm: MainTopicAddViewModel = viewModel()
) {


    if (vm.added.value) {
        navController.navigate(Screens.MainTopicListScreen.url)
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
                onClick = { navController.navigate(Screens.MainTopicListScreen.url) }) {
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
    }
}

@ExperimentalComposeUiApi
@Composable
fun mainTopicAddItem(navController: NavController, vm: MainTopicAddViewModel = viewModel(), added : (() -> Unit)? = null) {
    if (vm.added.value) {
        if(added == null)
            navController.navigate(Screens.MainTopicListScreen.url)
        else
            added()
        vm.setAdded(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
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
            label = { Text("제목을 입력해주세요.") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }),
        )
        Spacer(modifier = Modifier.height(30.dp))
        datePickerTextView(label = "시작일", text = startDate, datePicker = startDatePicker)
        Spacer(modifier = Modifier.height(30.dp))
        datePickerTextView(label = "종료일", text = endDate, datePicker = endDatePicker)
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




