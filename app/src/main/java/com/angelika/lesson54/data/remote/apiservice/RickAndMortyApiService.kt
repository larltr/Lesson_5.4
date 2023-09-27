package com.angelika.lesson54.data.remote.apiservice

import com.angelika.lesson54.models.CharacterModel
import com.angelika.lesson54.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyApiService {

    @GET("character")
    fun fetchCharacters(): Call<RickAndMortyResponse<CharacterModel>>
}