package com.example.marveldex.ui.features.characterlist

import androidx.lifecycle.ViewModel
import com.example.marveldex.api.MarvelClient
import com.example.marveldex.data.heroes.MarvelHeroResponse
import com.example.marveldex.data.network.Resource
import com.example.marveldex.data.network.ResponseHandler

class ListCharactersViewModel() : ViewModel() {

    suspend fun fetchMarvelHeroes(offset: Int) : Resource<MarvelHeroResponse> {
        return try {
            ResponseHandler.handleSuccess(MarvelClient.getMarvelHeroes(20, offset))
        } catch (e: Exception) {
            ResponseHandler.handleException(e)
        }
    }
}
