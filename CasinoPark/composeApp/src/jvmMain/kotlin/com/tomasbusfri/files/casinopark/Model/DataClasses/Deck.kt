package com.tomasbusfri.files.casinopark.Model.DataClasses

data class Deck(
    val deck_id: Int,
    val nCrads: Int,
    val cards: MutableList<Card>,
)
