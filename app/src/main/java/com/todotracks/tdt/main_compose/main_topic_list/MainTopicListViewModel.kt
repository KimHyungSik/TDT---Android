package com.todotracks.tdt.main_compose.main_topic_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todotracks.tdt.dto.MainTopic
import com.todotracks.tdt.main_compose.TopicListRepository
import com.todotracks.tdt.main_compose.TopicListRepositoryImp
import kotlinx.coroutines.launch

class MainTopicListViewModel : ViewModel() {
    val topicApi : TopicListRepository = TopicListRepositoryImp()
    private val _mainTopicList = mutableStateOf<List<MainTopic>>(emptyList())
    val mainTopicList : State<List<MainTopic>> = _mainTopicList

    init {
        getMainTopicList()
    }

    fun getMainTopicList(){
        viewModelScope.launch {
            _mainTopicList.value = topicApi.getMainTopicList().main_topics
        }
    }
}