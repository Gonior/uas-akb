package com.example.gniar_animelist.menus

import Anime
import android.icu.text.NumberFormat
import android.icu.text.NumberFormat.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gniar_animelist.R
import com.example.gniar_animelist.retrofitest.helpers.AnimeAdapter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_anime.*
import kotlinx.android.synthetic.main.anime_item.*
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.*

class DetailAnime : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_detail_anime)
        var bundle :Bundle? = intent.extras
        var mal_id = bundle!!.getString("mal_id")

        fetchApi("https://api.jikan.moe/v3/anime/$mal_id")
        btnAddToList.setOnClickListener {
            Toast.makeText(this, "Coming Soon!", Toast.LENGTH_LONG).show()
        }

    }
    private fun fetchApi(url : String) {
        containerDetailAnime.visibility = View.GONE
        progressBarDetailAnime.visibility = View.VISIBLE

        val request = Request.Builder().url(url).build()

        val client  = OkHttpClient()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("myFetch", "something went wrong")
                progressBarDetailAnime.visibility = View.GONE
                Toast.makeText(this@DetailAnime, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response?.body()?.string()
//                Log.d("myLog", body.toString())
                val anime = Gson().fromJson(body.toString(), Anime::class.java)
//                Log.d("myLog", anime.toString())

                runOnUiThread {

                    progressBarDetailAnime.visibility = View.GONE
                    containerDetailAnime.visibility = View.VISIBLE
                    Picasso.get().load(anime.image_url).into(ivImage)
                    tvTitleDetailAnime.text = anime.title
                    tvScoreDetailAnime.text = anime.score.toString()
                    tvRank.text = "#${anime.rank.toString()}"
                    tvPopularity.text = "#${anime.popularity.toString()}"
                    tvFavorites.text = getInstance(Locale.US).format(anime.favorites)
                    tvMembersDetailAnime.text = getInstance(Locale.US).format(anime.members)
                    tvSynopsis.text = anime.synopsis
                    tvTypeAndYear.text = "${anime.type},${anime.aired.prop.from.year}"
                    tvStatus.text = anime.status
                    tvEpisode.text = anime.episodes.toString()
                    tvDuration.text = anime.duration
                    tvSource.text = anime.source
                    tvRating.text = anime.rating
                    tvAired.text =anime.aired.string
                    tvTitleEnglish.text = anime.title_english
                    tvJapanTitle.text = anime.title_japanese
                    tvSeason.text = anime.premiered
                    tvStudio.text = listToString(anime, "studios")
                    tvGenreDetailAnime.text = listToString(anime, "genres")
                    tvOstOp.text = listToString(anime, "opening_themes")
                    tvOstEnd.text = listToString(anime, "ending_themes")
                    tvSynonyms.text = listToString(anime, "title_synonyms")


                }


            }


        })

    }

    private fun listToString(anime : Anime, keyword : String) : String  {
        val str = StringBuilder()

        when (keyword) {
            "studios" -> {
                for (i in anime.studios.indices) {
                    str.append(anime.studios[i].name)

                    if (i != anime.studios.size-1) {
                        str.append(", ")
                    }
                }
            }
            "genres" -> {
                for (i in anime.genres.indices) {
                    str.append(anime.genres[i].name)

                    if (i != anime.genres.size-1) {
                        str.append("  -  ")
                    }
                }
            }
            "opening_themes" -> {
                for (i in anime.opening_themes.indices) {
                    str.append(anime.opening_themes[i])

                    if (i != anime.opening_themes.size-1) {
                        str.append(", ")
                    }
                }
            }
            "ending_themes" -> {
                for (i in anime.ending_themes.indices) {
                    str.append(anime.ending_themes[i])

                    if (i != anime.ending_themes.size-1) {
                        str.append(", ")
                    }
                }
            }
            "title_synonyms" -> {
                for (i in anime.title_synonyms.indices) {
                    str.append(anime.title_synonyms[i])

                    if (i != anime.title_synonyms.size-1) {
                        str.append(", ")
                    }
                }
            }

        }

        return str.toString()

    }
}