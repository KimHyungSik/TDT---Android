package com.todotracks.tdt.main_compose.main_topic_added

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todotracks.tdt.main_compose.TopicListRepository
import com.todotracks.tdt.main_compose.TopicListRepositoryImp
import com.todotracks.tdt.main_compose.common.Event
import com.todotracks.tdt.model.MainTopic
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainTopicAddViewModel : ViewModel() {
    val topicApi : TopicListRepository = TopicListRepositoryImp()
    private val _added = mutableStateOf(false)
    val added : State<Boolean> = _added

    fun addTopic(mainTopic: MainTopic){
        viewModelScope.launch {
            val unit = topicApi.addedMainTopic(mainTopic)
            _added.value = true
        }
    }

    fun setAdded(boolean: Boolean){
        _added.value = boolean
    }
}