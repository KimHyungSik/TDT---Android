package com.todotracks.tdt.src.map.model

import com.google.gson.annotations.SerializedName

data class PostSubRequest(
    @SerializedName("main_no") var main_no: Int,
    @SerializedName("title") var title: String,
    @SerializedName("plan_dt") var plan_dt: String,
    @SerializedName("description") var description: String?,
    @SerializedName("latitude") var latitude: Float,
    @SerializedName("longitude") var longitude: Float,
    @SerializedName("address") var address: String?
)

//{
//    "main_no": 1, // 메인 토픽 no
//    "title": "소주제 이름",
//    "plan_dt": "2021-01-01T01:00",
//    "description": "메모", // 필수아님
//    "latitude": 25.3, // 위도
//    "longitude": 35.2, // 경도
//    "address": "서울시 어딘가"// 주소, 필수아님
//}