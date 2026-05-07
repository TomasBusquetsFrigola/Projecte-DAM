package com.tomasbusfri.files.casinopark.Model.API.APIClasses


import com.google.gson.annotations.SerializedName

data class APICard(
    @SerializedName("cards")
    val cards: List<IndividualAPICard>,
    @SerializedName("deck_id")
    val deckId: String,
    @SerializedName("remaining")
    val remaining: Int,
    @SerializedName("success")
    val success: Boolean
)