package com.example.mywishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywishlistapp.Data.Wish
import com.example.mywishlistapp.Data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(private val WishRepository: WishRepository = Graph.wishRepository
): ViewModel() {

    var WishTitleState by mutableStateOf("")
    var WishDescriptionState by mutableStateOf("")

    fun WishTitleChange(newString: String) {
        WishTitleState = newString            // the updated title of wish will be stored in WishTitleState
    }

    fun WishDescriptionChange(newString: String) {
        WishDescriptionState = newString     // the updated description of wish will be stored in WishDescriptionState
    }

    lateinit var getAllWish : Flow<List<Wish>>
// since Flow is playing the role so this is an asynchronous variable declaration thats why we are using lateinit
    init {
        viewModelScope.launch {
            getAllWish = WishRepository.getAllWish()
        }
    }

    fun addAWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            WishRepository.addAWish(wish = wish)
        }
    }

    fun updateAWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            WishRepository.updateAWish(wish = wish)
        }
    }

    fun getAWishbyid(id: Long) : Flow<Wish> {
        return WishRepository.getAWishbyid(id)
    }

    fun deleteAWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            WishRepository.deleteAWish(wish = wish)
        }
    }

}