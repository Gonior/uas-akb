package com.example.gniar_animelist.adapter

import Results
import android.content.Context
import android.content.Intent
import android.icu.text.NumberFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gniar_animelist.R
import com.example.gniar_animelist.menus.DetailAnime
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.util.*


class ListAnimeSearchAdapter(private val animeList: List<Results>) :RecyclerView.Adapter<ListAnimeSearchAdapter.ViewHolder>() {
    private var context : Context? = null
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var btnView = itemView.findViewById<RelativeLayout>(R.id.containerItemAnimeSearch)
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitleAnimeSearch)
        var tvScore = itemView.findViewById<TextView>(R.id.tvScoreAnimeSearch)
        var tvMember = itemView.findViewById<TextView>(R.id.tvMemberAnimeSearch)
        var img = itemView.findViewById<ImageView>(R.id.imgAnimeSearch)
        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(anime: Results, context: Context) {
            btnView.setOnClickListener {

                val intent = Intent(context, DetailAnime::class.java)
                intent.putExtra("mal_id", anime.mal_id.toString())
                startActivity(context, intent, null)
            }
            tvTitle.text = anime.title
            tvScore.text = anime.score.toString()
            tvMember.text = NumberFormat.getInstance(Locale.US).format(anime.members)
            Picasso.get().load(anime.image_url).into(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_list_search,parent,false)

        return ViewHolder(view)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return context?.let { holder.bind(animeList[position], it) }!!
    }

    override fun getItemCount(): Int {
        return animeList.size
    }

}
