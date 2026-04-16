package com.tomasbusfri.files.casinopark.Model.DataClasses

import com.tomasbusfri.files.casinopark.Model.API.APIClasses.IndividualAPICard

data class Card(
    val cardValue: Int,
    val cardSuit: String,
    val cardSide: String,
)

fun IndividualAPICard.toCard(): Card = Card(
    cardValue = value.toInt(),
    cardSuit = suit,
    cardSide = images.svg
)