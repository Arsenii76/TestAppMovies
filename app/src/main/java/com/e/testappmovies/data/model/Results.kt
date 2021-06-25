package com.e.testappmovies.data.model

import com.google.gson.annotations.SerializedName

data class Results (
    @SerializedName("display_title") val title: String,
    @SerializedName("summary_short") val description: String,
    val multimedia: Multimedia
)