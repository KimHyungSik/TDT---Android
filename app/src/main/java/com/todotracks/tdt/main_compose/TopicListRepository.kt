package com.todotracks.tdt.main_compose

import com.todotracks.tdt.dto.MainTopicsDto
import com.todotracks.tdt.kotlin.config.ApplicationClass
import com.todotracks.tdt.model.MainTopic
import retrofit2.Response

interface TopicListRepository{
    suspend fun getMainTopicList() : MainTopicsDto
    suspend fun addedMainTopic(mainTopic: MainTopic) : Response<Unit>
}

class TopicListRepositoryImp :  TopicListRepository{
    val topicApi = ApplicationClass.sRetrofit.create(TopicListApi::class.java)
    override suspend fun getMainTopicList(): MainTopicsDto = topicApi.getMainTopics()
    override suspend fun addedMainTopic(mainTopic: MainTopic): Response<Unit> = topicApi.addMainTopics(mainTopic = mainTopic)
}