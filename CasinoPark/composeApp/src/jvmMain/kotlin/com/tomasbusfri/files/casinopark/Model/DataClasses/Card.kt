package com.tomasbusfri.files.casinopark.Model.DataClasses

import com.tomasbusfri.files.casinopark.Model.API.APIClasses.IndividualAPICard

data class Card(
    val cardValue: Int,
    val cardSuit: String,
    val cardSide: String,
    val cardColor: CardColor
)

enum class CardColor {
    BLACK, RED
}

fun IndividualAPICard.toCard(): Card = Card(
    cardValue = value.toInt(),
    cardSuit = suit,
    cardSide = images.svg,
    cardColor =
        if (suit == "SPADES" || suit == "CLUBS") CardColor.BLACK
        else if (suit == "DIAMONDS" || suit == "HEARTS") CardColor.RED
        else throw Exception(),
)