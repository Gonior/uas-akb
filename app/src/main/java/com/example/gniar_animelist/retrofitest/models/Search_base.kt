package com.example.gniar_animelist.retrofitest.models

import Anime
import Results
import com.google.gson.annotations.SerializedName


data class Search_base (
        @SerializedName("request_hash") val request_hash : String,
        @SerializedName("request_cached") val request_cached : Boolean,
        @SerializedName("request_cache_expiry") val request_cache_expiry : Int,
        @SerializedName("results") val results : List<Results>,
        @SerializedName("last_page") val last_page : Int
        )

