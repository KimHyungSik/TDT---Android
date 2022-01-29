package com.todotracks.tdt.src.user_settings.model

import com.google.gson.annotations.SerializedName

data class loginRequest(
    @SerializedName("device_id") var device_id: String
)