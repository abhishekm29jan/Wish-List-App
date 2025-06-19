package com.example.mywishlistapp.Data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {
    suspend fun addAWish(wish: Wish )  {  // Suspend function to perform database operations on a background thread
        wishDao.addAWish(wish)
    }

    fun getAllWish() : Flow<List<Wish>> {  // Flow to observe changes in the database
        return wishDao.getAllWish()
    }

    fun getAWishbyid(id: Long) : Flow<Wish> {
        return wishDao.getAWishbyid(id)
    }

    suspend fun updateAWish(wish: Wish) {
        wishDao.updateAWish(wish)
    }

    suspend fun deleteAWish(wish: Wish) {
        wishDao.deleteAWish(wish)

    }
}