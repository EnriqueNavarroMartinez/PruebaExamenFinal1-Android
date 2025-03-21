package com.example.examenfinal1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examenfinal1.database.CharacterApplication

import com.example.examenfinal1.R
import com.example.examenfinal1.api.RickAndMortyCharacter
import com.example.examenfinal1.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class CharacterAdapter (private val caracters: List<RickAndMortyCharacter>): RecyclerView.Adapter<CharacterAdapter.ViewHolder>(){
    private lateinit var context: Context
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        // Inicializamos el binding (item)
        val binding = ItemCharacterBinding.bind(view)
        // Llamamos a onClickListener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.ViewHolder {
        // Inflamos la vista del Intem del Recycler
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.ViewHolder, position: Int) {

            val character = caracters.get(position)

            with(holder){
                binding.nombre.text = character.name
                binding.alive.text = character.status
                Picasso.get().load(character.image).into(binding.image)
                Thread {

                    if (character.status == "Alive") {
                        CharacterApplication.database.characterDao().delete(caracters)
                        CharacterApplication.database.characterDao().insert(character)
                    }
                }.start()
            }


    }

    // Se hace pero no se para que es
    override fun getItemCount(): Int = caracters.size


}