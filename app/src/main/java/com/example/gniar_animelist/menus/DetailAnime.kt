package com.example.gniar_animelist.menus

import Anime
import android.icu.text.NumberFormat.*
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gniar_animelist.R
import com.example.gniar_animelist.retrofitest.helpers.AnimeDBHelper
import com.example.gniar_animelist.retrofitest.models.AnimeModeLDb
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
    private val Helper = AnimeDBHelper(this)
    private var animeForDB : AnimeModeLDb? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_detail_anime)
        var bundle :Bundle? = intent.extras
        var mal_id = bundle!!.getString("mal_id")


        fetchApi("https://api.jikan.moe/v3/anime/$mal_id")

        btnAddToList.setOnClickListener {
            animeForDB?.let { it1 -> Helper.populateAnime(it1) }
            Toast.makeText(this, "Succesfully added to My List", Toast.LENGTH_LONG).show()
            if (mal_id != null) {
                detectList(mal_id)
            }
        }
        btnRemoveFromlist.setOnClickListener {
            if (mal_id != null) {
                Helper.removeFavorite(mal_id)
                Toast.makeText(this, "Succesfully remove from My List", Toast.LENGTH_LONG).show()
                detectList(mal_id)
            }
        }
        btnRefreshDetailAnime.setOnClickListener {
            fetchApi("https://api.jikan.moe/v3/anime/$mal_id")
        }
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun detectList(mal_id : String) {
        val hasBeenAdded = Helper.findAnimeById(mal_id)
        if (hasBeenAdded) {
            btnAddToList.visibility = View.GONE
            btnRemoveFromlist.visibility = View.VISIBLE
        }
        else {
            btnAddToList.visibility = View.VISIBLE
            btnRemoveFromlist.visibility = View.GONE
        }
    }
    private fun fetchApi(url : String) {
        containerErrorDetailAnime.visibility = View.GONE
        val request = Request.Builder().url(url).build()

        val client  = OkHttpClient()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {

                runOnUiThread {
                    progressBarDetailAnime.visibility = View.GONE
                    containerErrorDetailAnime.visibility = View.VISIBLE
                    Toast.makeText(this@DetailAnime, "Something went wrong", Toast.LENGTH_SHORT).show()
                }

            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response?.body()?.string()
                val anime = Gson().fromJson(body.toString(), Anime::class.java)

                runOnUiThread {

                    containerDetailAnime.visibility = View.VISIBLE
                    progressBarDetailAnime.visibility = View.GONE
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

                    animeForDB = AnimeModeLDb(anime.mal_id, anime.title, anime.image_url, listToString(anime,"genresToString"), anime.score, anime.members)
                    detectList(anime.mal_id.toString())
                }


            }


        })

    }

    private fun listToString(anime : Anime, keyword : String) : String  {
        val str = StringBuilder()

        when (keyword) {
            "studios" -> {
                if(anime.studios.isNotEmpty()) {
                    for (i in anime.studios.indices) {
                        str.append(anime.studios[i].name)

                        if (i != anime.studios.size-1) {
                            str.append("\n")
                        }
                    }
                } else str.append("-")

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
                if(anime.opening_themes.isNotEmpty()) {
                    for (i in anime.opening_themes.indices) {
                        str.append(anime.opening_themes[i])

                        if (i != anime.opening_themes.size-1) {
                            str.append("\n")
                        }
                    }
                } else str.append("-")

            }
            "ending_themes" -> {
                if (anime.ending_themes.isNotEmpty()) {
                    for (i in anime.ending_themes.indices) {
                        str.append(anime.ending_themes[i])

                        if (i != anime.ending_themes.size-1) {
                            str.append("\n")
                        }
                    }
                } else str.append("-")


            }
            "title_synonyms" -> {
                if (anime.title_synonyms.isNotEmpty()) {
                    for (i in anime.title_synonyms.indices) {
                        str.append(anime.title_synonyms[i])

                        if (i != anime.title_synonyms.size-1) {
                            str.append("\n")
                        }
                    }
                } else {
                    str.append("-")
                }

            }

            "genresToString" -> {
                for (i in anime.genres.indices) {
                    str.append(anime.genres[i].name)

                    if (i != anime.genres.size-1) {
                        str.append(", ")
                    }
                }
            }
        }

        return str.toString()

    }
}