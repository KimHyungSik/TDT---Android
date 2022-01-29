package com.todotracks.tdt.main_compose.common

sealed class Screens(val url : String){
    object MainTopicListScreen : Screens("main_topic_list")
    object MainTopicAddedScreen : Screens("main_topic_added")
    object SubTopicAddedScreen : Screens("sub_topic_added")
}