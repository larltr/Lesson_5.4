package com.angelika.lesson54.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angelika.lesson54.data.repositories.RickAndMortyRepository
import com.angelika.lesson54.models.CharacterModel

class RickAndMortyViewModel : ViewModel() {

    private val rickAndMortyRepository = RickAndMortyRepository()

    private val _characterLiveData = MutableLiveData(CharacterUiState<List<CharacterModel>>())
    val characterLiveData: LiveData<CharacterUiState<List<CharacterModel>>> = _characterLiveData

    fun getData() {
        rickAndMortyRepository.getData(
            onResponse = { data ->
                val newValue =
                    _characterLiveData.value?.copy(isLoading = false, success = data.results)
                _characterLiveData.value = newValue
            },
            onFailure = { message ->
                val newValue = _characterLiveData.value?.copy(isLoading = false, error = message)
                _characterLiveData.value = newValue
            }
        )
    }
}

data class CharacterUiState<T>(
    val isLoading: Boolean = true,
    val error: String? = null,
    val success: T? = null
)