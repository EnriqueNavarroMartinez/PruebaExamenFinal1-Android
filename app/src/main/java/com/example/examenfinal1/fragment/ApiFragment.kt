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
import com.example.examenfinal1.databinding.FragmentApiBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApiFragment : Fragment() {

    //Declaramos la variablesa utilizar
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var binding: FragmentApiBinding
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inicializamos el binding directamente
        binding = FragmentApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.api.getAllCharacters()

            // Verifica si la respuesta fue exitosa
            if (response.results != null) {
                val characters = response.results

                // Log para mostrar cada personaje recibido
                characters.forEach { character ->
                    Log.d("CHARACTER_LIST", "Nombre: ${character.name}, Estado: ${character.status}, Especie: ${character.species}")
                }

                withContext(Dispatchers.Main) {
                    characterAdapter = CharacterAdapter(characters)
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