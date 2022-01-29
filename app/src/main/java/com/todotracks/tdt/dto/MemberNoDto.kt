package com.todotracks.tdt.dto

import com.google.gson.annotations.SerializedName

data class MemberNoDto(
    @SerializedName("member_no")
    val member_no: Int
)