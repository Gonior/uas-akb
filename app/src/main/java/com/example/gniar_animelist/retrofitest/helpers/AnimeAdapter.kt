package com.example.gniar_animelist.retrofitest.helpers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.gniar_animelist.R
import com.example.gniar_animelist.retrofitest.models.Anime

class AnimeAdapter(private val animeList: List<Anime>) :RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.anime_item,parent,false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return animeList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${animeList.size} ")


        return holder.bind(animeList[position])

    }
    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {


        var imageView = itemView.findViewById<ImageView>(R.id.imageAnime)
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvScore = itemView.findViewById<TextView>(R.id.tvScore)
        fun bind(anime: Anime) {

            val name ="Cases :${anime.score.toString()}"
            tvTitle.text = anime.title
            tvScore.text = name
            Picasso.get().load(anime.image_url).into(imageView)
        }

    }
}