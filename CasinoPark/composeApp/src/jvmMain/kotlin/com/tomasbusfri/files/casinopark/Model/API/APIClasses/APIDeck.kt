package com.tomasbusfri.files.casinopark.Model.API.APIClasses


import com.google.gson.annotations.SerializedName

data class APIDeck(
    @SerializedName("deck_id")
    val deckId: String,
    @SerializedName("remaining")
    val remaining: Int,
    @SerializedName("shuffled")
    val shuffled: Boolean,
    @SerializedName("success")
    val success: Boolean
)