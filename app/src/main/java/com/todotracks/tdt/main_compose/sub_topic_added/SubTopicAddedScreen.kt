package com.todotracks.tdt.main_compose.sub_topic_added

import android.util.Log
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.todotracks.tdt.main_compose.common.DatePickerview
import com.todotracks.tdt.main_compose.component.datePickerTextView
import com.todotracks.tdt.ui.theme.Indigo

@ExperimentalComposeUiApi
@Composable
fun subTopicAddedScreen(
    vm : SubTopicViewModel = viewModel(),
    navController: NavController,
    mainTopicId : Int?,
    mainTopicTitle : String?
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var title by remember {
        mutableStateOf("")
    }
    val (dateState, datePicker) = DatePickerview()
    var date by remember {
        dateState
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
                text = mainTopicTitle ?: "여행 없음",
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
        ) {
            Spacer(modifier = Modifier.height(80.dp))
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
                    onDone = {keyboardController?.hide()}),
            )
            Spacer(modifier = Modifier.height(30.dp))
            datePickerTextView(label = "여행일" ,text = date, datePicker = datePicker)
        }
    }
}