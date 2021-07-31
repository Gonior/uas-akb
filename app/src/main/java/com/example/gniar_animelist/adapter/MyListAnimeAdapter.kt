package com.example.gniar_animelist.adapter




import android.content.Context
import android.content.Intent
import android.icu.text.NumberFormat
import android.os.Build

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.gniar_animelist.R
import com.example.gniar_animelist.menus.DetailAnime
import com.example.gniar_animelist.retrofitest.models.AnimeModeLDb
import java.util.*


class MyListAnimeAdapter(private val animeList: List<AnimeModeLDb>) :RecyclerView.Adapter<MyListAnimeAdapter.ViewHolder>() {
    private var context : Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.anime_item,parent,false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return animeList.size
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        return context?.let { holder.bind(animeList[position], it) }!!

    }
    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {


        var imageView = itemView.findViewById<ImageView>(R.id.imageAnime)
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvScore = itemView.findViewById<TextView>(R.id.tvScore)
        var tvMember = itemView.findViewById<TextView>(R.id.tvMember)
        var tvGenre = itemView.findViewById<TextView>(R.id.tvGenre)
        var tvBtnViewMore = itemView.findViewById<AppCompatButton>(R.id.btnViewMore)

        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(anime: AnimeModeLDb, context: Context) {

            tvBtnViewMore.setOnClickListener {
                val intent = Intent(context, DetailAnime::class.java)
                intent.putExtra("mal_id", anime.mal_id.toString())
                startActivity(context, intent, null)
            }
            val name ="Score ${anime.score.toString()}"
            val members = "Member ${NumberFormat.getInstance(Locale.US).format(anime.members)}"
            tvTitle.text = anime.title
            tvScore.text = name
            tvMember.text = members
            tvGenre.text = anime.genre
            Picasso.get().load(anime.img).into(imageView)
        }

    }
}
