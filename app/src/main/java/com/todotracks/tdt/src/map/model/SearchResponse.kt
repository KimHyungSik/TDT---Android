package com.todotracks.tdt.src.map.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("items") var items: ArrayList<search_list>
)