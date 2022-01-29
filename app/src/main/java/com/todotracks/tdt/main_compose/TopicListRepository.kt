package com.todotracks.tdt.main_compose

import com.todotracks.tdt.dto.MainTopicsDto
import com.todotracks.tdt.dto.SubTopicsDto
import com.todotracks.tdt.kotlin.config.ApplicationClass
import com.todotracks.tdt.model.MainTopic
import com.todotracks.tdt.model.SubTopic
import retrofit2.Response

interface TopicListRepository{
    suspend fun getMainTopicList() : MainTopicsDto
    suspend fun addedMainTopic(mainTopic: MainTopic) : Response<Unit>
    suspend fun addSubTopic(subTopic: SubTopic) : Response<Unit>
    suspend fun getSubTopicList(main_no: Int, date : String) : SubTopicsDto
}

class TopicListRepositoryImp :  TopicListRepository{
    val topicApi = ApplicationClass.sRetrofit.create(TopicListApi::class.java)
    override suspend fun getMainTopicList(): MainTopicsDto = topicApi.getMainTopics()
    override suspend fun addedMainTopic(mainTopic: MainTopic): Response<Unit> = topicApi.addMainTopics(mainTopic = mainTopic)
    override suspend fun addSubTopic(subTopic: SubTopic): Response<Unit> = topicApi.addSubTopics(subTopic = subTopic)
    override suspend fun getSubTopicList(main_no: Int, date: String): SubTopicsDto = topicApi.getSubTopics(main_no, date)
}