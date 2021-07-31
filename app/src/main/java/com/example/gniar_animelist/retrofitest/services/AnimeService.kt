package com.example.gniar_animelist.retrofitest.services


import Anime

import retrofit2.Call
import retrofit2.http.GET

interface AnimeService {
    @GET("season")
    fun getAffectedAnimeList () : Call<List<Anime>>
}