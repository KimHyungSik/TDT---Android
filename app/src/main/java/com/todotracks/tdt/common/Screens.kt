package com.todotracks.tdt.common

sealed class Screens(val url : String){
    object MainTopicListScreen : Screens("main_topic_list")
}