package com.todotracks.tdt.src.user_settings

var token_id = ""
var user_Id: Int? = null

fun my_device_token(token: String){
    token_id = token
}

fun device_token(): String{
    return token_id
}

fun my_user_Id(userId: Int){
    user_Id = userId
}

fun user_Id(): Int {
    return user_Id!!
}