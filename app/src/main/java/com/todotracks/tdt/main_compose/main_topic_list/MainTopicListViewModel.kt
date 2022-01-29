package com.todotracks.tdt.main_compose.main_topic_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todotracks.tdt.dto.MainTopicDto
import com.todotracks.tdt.main_compose.TopicListRepository
import com.todotracks.tdt.main_compose.TopicListRepositoryImp
import kotlinx.coroutines.launch

class MainTopicListViewModel : ViewModel() {
    val topicApi : TopicListRepository = TopicListRepositoryImp()
    private val _mainTopicList = mutableStateOf<List<MainTopicDto>>(emptyList())
    val mainTopicDtoList : State<List<MainTopicDto>> = _mainTopicList

    init {
        getMainTopicList()
    }

    fun getMainTopicList(){
        viewModelScope.launch {
            _mainTopicList.value = topicApi.getMainTopicList().main_topicDtos
        }
    }
}