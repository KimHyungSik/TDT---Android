package com.todotracks.tdt.main_compose.component

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.todotracks.tdt.ui.theme.Gray
import com.todotracks.tdt.ui.theme.GrayLight

@Composable
fun datePickerTextView(label: String? = null, text: String, datePicker: DatePickerDialog) {
    Column() {
        if (label != null) {
            Text(text = label)
            Spacer(modifier = Modifier.height(3.dp))
        }

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
}