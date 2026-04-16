package com.tomasbusfri.files.casinopark.Model.DataClasses

import com.tomasbusfri.files.casinopark.Model.API.APIClasses.APICard
import com.tomasbusfri.files.casinopark.Model.API.APIClasses.APIDeck
import com.tomasbusfri.files.casinopark.Model.API.APIClasses.IndividualAPICard

data class Deck(
    val deckId: String,
    val nCards: Int,
    val cards: List<Card>,
)

fun APIDeck.toDeck(): Deck  = Deck(
    deckId = deckId,
    nCards = remaining,
    cards = mutableListOf(),
)
