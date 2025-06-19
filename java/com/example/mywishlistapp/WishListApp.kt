package com.example.mywishlistapp

import android.app.Application

class WishListApp: Application() {
    override fun onCreate() {
        super.onCreate()
        //initialising the graph by calling the provide function ie the database and repository and passing the context/information of it.
        Graph.provide(this)

    }
}