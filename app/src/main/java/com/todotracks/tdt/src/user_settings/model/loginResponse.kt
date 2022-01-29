package com.todotracks.tdt.src.user_settings.model

import com.google.gson.annotations.SerializedName

data class loginResponse(
    @SerializedName("member_no") var member_no: Int
)