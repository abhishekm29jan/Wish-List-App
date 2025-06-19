package com.example.mywishlistapp

// This implies that there are two screens in our app
sealed class Screen(val route : String){         // sealed class means it cant be inherited
    object HomeScreen : Screen("home_screen")
    object AddScreen : Screen("add_screen")
}