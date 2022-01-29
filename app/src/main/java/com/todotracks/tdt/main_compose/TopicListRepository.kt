package com.todotracks.tdt.main_compose

import com.todotracks.tdt.dto.MainTopics
import com.todotracks.tdt.kotlin.config.ApplicationClass

interface TopicListRepository{
    suspend fun getMainTopicList() : MainTopics
}

class TopicListRepositoryImp :  TopicListRepository{
    val topicApi = ApplicationClass.sRetrofit.create(TopicListApi::class.java)
    override suspend fun getMainTopicList(): MainTopics = topicApi.getMainTopics()

}