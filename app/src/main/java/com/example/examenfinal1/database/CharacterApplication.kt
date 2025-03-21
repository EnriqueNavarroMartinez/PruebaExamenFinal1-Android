package com.example.examenfinal1.database

import android.app.Application
import androidx.room.Room

class CharacterApplication : Application(){
    companion object{
        lateinit var database: ChatacterDatabase
    }
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
            ChatacterDatabase::class.java,
            "ChatacterDatabase")
            .build()
    }
}