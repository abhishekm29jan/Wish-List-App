package com.example.mywishlistapp.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAWish(wishEntity: Wish)      //CREATE

    // Loads all wishes from a wish table
    @Query("SELECT * from `wish-table`")
    abstract fun getAllWish() : Flow<List<Wish>>         // READ

    @Update
    abstract suspend fun updateAWish(wishEntity: Wish)      // UPDATE

    @Delete
    abstract suspend fun deleteAWish(wishEntity: Wish)       // DELETE

    @Query("SELECT * from `wish-table` WHERE id = :id")
    abstract fun getAWishbyid(id: Long) : Flow<Wish>
}