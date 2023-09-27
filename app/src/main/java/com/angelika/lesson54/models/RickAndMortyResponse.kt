package com.angelika.lesson54.models

import android.icu.text.IDNA
import com.google.gson.annotations.SerializedName

data class RickAndMortyResponse<T>(

    @SerializedName("info")
    val info: IDNA.Info,

    @SerializedName("results")
    val results: List<T>,
)
