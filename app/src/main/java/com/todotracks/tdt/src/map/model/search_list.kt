package com.todotracks.tdt.src.map.model

import com.google.gson.annotations.SerializedName

data class search_list(
    @SerializedName("title") var title: String?,
    @SerializedName("category") var category: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("address") var address: String?,
    @SerializedName("roadAddress") var roadAddress: String?
)

//"title": "<b>김부삼</b> 한양대점",
//"category": "육류,고기요리>돼지고기구이",
//"description": "",
//"address": "서울특별시 성동구 행당동 19-43",
//"roadAddress": "서울특별시 성동구 마조로3가길 18" // 도로명주소