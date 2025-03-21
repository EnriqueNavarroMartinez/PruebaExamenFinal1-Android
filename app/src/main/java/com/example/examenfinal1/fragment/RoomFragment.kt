package com.example.examenfinal1.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenfinal1.R
import com.example.examenfinal1.adapter.CharacterAdapter
import com.example.examenfinal1.api.RetrofitClient
import com.example.examenfinal1.database.CharacterApplication
import com.example.examenfinal1.databinding.FragmentRoomBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomFragment : Fragment() {
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var binding: FragmentRoomBinding
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inicializamos el binding directamente
        binding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.api.getAllCharacters()

            // Verifica si la respuesta fue exitosa
            if (response.results != null) {
                // Filtra los personajes que tengan status "Alive"
                val aliveCharacters = response.results.filter { character ->
                    character.status.equals("Alive", ignoreCase = true)
                }

                // Log para mostrar cada personaje con status "Alive"
                aliveCharacters.forEach { character ->
                    Log.d("CHARACTER_LIST", "Nombre: ${character.name}, Estado: ${character.status}, Especie: ${character.species}")
                }

                withContext(Dispatchers.Main) {
                    characterAdapter = CharacterAdapter(aliveCharacters)
                    linearLayoutManager = LinearLayoutManager(requireContext())
                    binding.recycler.apply {
                        layoutManager = linearLayoutManager
                        adapter = characterAdapter
                    }
                }
            } else {
                Log.e("CHARACTER_LIST", "Error al obtener personajes")
            }
        }


    }
}