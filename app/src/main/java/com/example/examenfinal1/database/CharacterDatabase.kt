package com.example.examenfinal1.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examenfinal1.api.RickAndMortyCharacter
import com.example.examenfinal1.dao.CharacterDao


@Database(entities = [RickAndMortyCharacter::class], version = 1)
abstract class ChatacterDatabase : RoomDatabase(){
    abstract fun characterDao(): CharacterDao
}