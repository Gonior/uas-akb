package com.example.gniar_animelist.retrofitest.models

import com.google.gson.annotations.SerializedName

data class CharaStaff (
        @SerializedName("characters") val characters : List<Character>
        )

data class Character (
        @SerializedName("mal_id") val mal_id : Int,
        @SerializedName("image_url") val image_url : String,
        @SerializedName("name") val name : String,
        @SerializedName("role") val role : String,
        @SerializedName("voice_actors") val voice_actors : List<VoiceActor>,
        )

data class  VoiceActor (
        @SerializedName("mal_id") val mal_id: Int,
        @SerializedName("image_url") val image_url : String,
        @SerializedName("name") val name : String
        )

