package com.example.gniar_animelist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gniar_animelist.R
import com.example.gniar_animelist.retrofitest.models.CharaStaff
import com.example.gniar_animelist.retrofitest.models.Character
import com.squareup.picasso.Picasso

class ListCharaStaffAdapter (private val charas : List<Character>) : RecyclerView.Adapter<ListCharaStaffAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var ivChara = itemView.findViewById<ImageView>(R.id.ivChara)
        var ivVA = itemView.findViewById<ImageView>(R.id.ivVA)
        var tvChara = itemView.findViewById<TextView>(R.id.tvChara)
        var tvVA = itemView.findViewById<TextView>(R.id.tvVa)
        fun bind(character : Character) {
            Picasso.get().load(character.image_url).into(ivChara)
            tvChara.text = character.name
            if (character.voice_actors.isNotEmpty()) {
                tvVA.visibility = View.VISIBLE
                ivVA.visibility = View.VISIBLE
                val image_url = character.voice_actors[0].image_url.replace("/r/42x62".toRegex()) { "" }
                Picasso.get().load(image_url).into(ivVA)
                tvVA.text = character.voice_actors[0].name
            } else {
                ivVA.visibility = View.GONE
                tvVA.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCharaStaffAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chara_staff_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(charas[position])
    }

    override fun getItemCount(): Int {
        return charas.size
    }
}