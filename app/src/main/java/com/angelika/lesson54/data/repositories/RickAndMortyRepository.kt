package com.angelika.lesson54.data.repositories

import com.angelika.lesson54.App
import com.angelika.lesson54.models.CharacterModel
import com.angelika.lesson54.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RickAndMortyRepository {

    fun getData(
        onResponse: (data: RickAndMortyResponse<CharacterModel>) -> Unit,
        onFailure: (errorMassage: String) -> Unit
    ) {
        App.retrofitClient.characterApi.fetchCharacters()
            .enqueue(object : Callback<RickAndMortyResponse<CharacterModel>> {
                override fun onResponse(
                    call: Call<RickAndMortyResponse<CharacterModel>>,
                    response: Response<RickAndMortyResponse<CharacterModel>>
                ) {
                    if (response.isSuccessful)
                        response.body()?.let(onResponse)
                }

                override fun onFailure(
                    call: Call<RickAndMortyResponse<CharacterModel>>,
                    t: Throwable
                ) {
                    onFailure(t.localizedMessage ?: "Error")
                }
            })
    }
}