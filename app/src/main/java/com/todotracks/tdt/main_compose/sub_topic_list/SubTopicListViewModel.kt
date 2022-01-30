package com.todotracks.tdt.main_compose.sub_topic_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todotracks.tdt.dto.SubTopicsDto
import com.todotracks.tdt.main_compose.TopicListRepository
import com.todotracks.tdt.main_compose.TopicListRepositoryImp
import kotlinx.coroutines.launch

class SubTopicListViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val topicApi : TopicListRepository = TopicListRepositoryImp()
    private val _subTopics = mutableStateOf<SubTopicsDto>(SubTopicsDto(0, emptyList()))
    val subTopics : State<SubTopicsDto> = _subTopics
    private val _completeRate = mutableStateOf<Int>(0)
    val completeRate : State<Int> = _completeRate

    var date: String = savedStateHandle.get<String>("date") ?: ""
    var mainTopicId : Int = savedStateHandle.get<Int>("main_topic_id") ?: 0

    init {

    }

    fun getSubTopics(mainTopicId : Int, date : String){
        viewModelScope.launch {
            _subTopics.value = topicApi.getSubTopicList(mainTopicId, date)
            _completeRate.value = subTopics.value.complete_rate
        }
    }

}