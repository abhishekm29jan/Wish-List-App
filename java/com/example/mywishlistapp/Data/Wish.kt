package com.example.mywishlistapp.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,                  // "id" is the unique identifier for each wish so this is the primary key
    @ColumnInfo(name = "wish-title")
    val title : String = "",
    @ColumnInfo(name = "wish-description")
    val description : String = ""
)

object DummyWish {
    val wishlist = listOf(
        Wish(title = "Google Pixel", description = "An Android Smartphone by Google"),
        Wish(title = "New Laptop", description = "A high-performance laptop for development work"),
        Wish(title = "Bookshelf", description = "A sturdy bookshelf for my growing book collection"),
        Wish(title = "Wireless Headphones", description = "Noise-cancelling headphones for focused work"),
        Wish(title = "Weekend Getaway", description = "A relaxing trip to the mountains")
    )
}