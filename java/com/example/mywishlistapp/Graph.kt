package com.example.mywishlistapp

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mywishlistapp.Data.WishDatabase
import com.example.mywishlistapp.Data.WishRepository

object Graph {
    lateinit var database: WishDatabase  //initialising the database here

    val wishRepository by lazy {
        //initialising the repository here by passing the dao from the database
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context) {
//building the database by passing the context and name of the database then assigning it to database
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wish-database").build()
    }
}