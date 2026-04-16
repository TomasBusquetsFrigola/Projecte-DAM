package com.tomasbusfri.files.casinopark.Model.DAO.Interfaces

import com.tomasbusfri.files.casinopark.Model.DataClasses.Card
import com.tomasbusfri.files.casinopark.Model.DataClasses.Deck

interface DAODeck {
    fun getDeckByID(deckId:Int): Deck?

    fun getCardsOfDeck(deckId: Int): List<Card>

    fun createDeck(deck: Deck): Boolean

    fun updateDeck(deck: Deck): Boolean

    fun deleteDeck(deck: Deck): Boolean

    fun commitDecks(): Boolean

    fun getAll(): List<Deck>
}