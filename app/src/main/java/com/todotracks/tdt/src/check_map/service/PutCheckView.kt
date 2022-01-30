package com.todotracks.tdt.src.check_map.service

interface PutCheckView{
    fun onPutCheckSuccess(response: Unit)
    fun onPutCheckFailure(message: Unit)
}