package com.example.dragonballz.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dragonballz.R
import com.squareup.picasso.Picasso

class CharacterAdapter(private val characters: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val ki: TextView = itemView.findViewById(R.id.ki)
        val race: TextView = itemView.findViewById(R.id.race)
        val affiliation: TextView = itemView.findViewById(R.id.affiliation)
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.name.text = character.name
        holder.ki.text = "KI: ${character.ki} / ${character.maxKi}"
        holder.race.text = "Raza: ${character.race}"
        holder.affiliation.text = "Afiliación: ${character.affiliation}"

        Picasso.get()
            .load(character.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.image)
    }


    override fun getItemCount(): Int = characters.size
}
