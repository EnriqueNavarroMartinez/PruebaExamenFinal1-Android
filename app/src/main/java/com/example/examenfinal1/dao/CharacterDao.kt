package com.example.examenfinal1.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.examenfinal1.api.RickAndMortyCharacter

@Dao
interface CharacterDao {
    @Insert
    fun insert(characters: RickAndMortyCharacter)

    @Query("SELECT * FROM characters")
    fun getAll(): List<RickAndMortyCharacter>

    @Delete
    fun delete(character: List<RickAndMortyCharacter>)
}