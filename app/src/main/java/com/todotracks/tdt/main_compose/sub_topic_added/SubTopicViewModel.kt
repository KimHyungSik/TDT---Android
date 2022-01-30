package com.todotracks.tdt.main_compose.sub_topic_added

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.todotracks.tdt.main_compose.TopicListRepository
import com.todotracks.tdt.main_compose.TopicListRepositoryImp
import com.todotracks.tdt.model.MainTopic
import com.todotracks.tdt.model.SubTopic
import kotlinx.coroutines.launch

class SubTopicViewModel : ViewModel() {
    val topicApi : TopicListRepository = TopicListRepositoryImp()
    private val _added = mutableStateOf(false)
    val added : State<Boolean> = _added

    fun addTopic(subTopic: SubTopic){
        viewModelScope.launch {
            val unit = topicApi.addSubTopic(subTopic)
            _added.value = true
        }
    }

    fun setAdded(boolean: Boolean){
        _added.value = boolean
    }
}