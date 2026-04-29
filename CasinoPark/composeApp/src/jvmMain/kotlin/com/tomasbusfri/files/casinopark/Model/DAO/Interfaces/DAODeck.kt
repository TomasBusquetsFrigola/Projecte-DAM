package com.tomasbusfri.files.casinopark.Model.DAO.Interfaces

import com.tomasbusfri.files.casinopark.Model.DataClasses.Card
import com.tomasbusfri.files.casinopark.Model.DataClasses.Deck

interface DAODeck {
    /**
     * Returns the deck with matching id
     *
     * @param deckId The id of the deck desired
     *
     * @return The deck desired if found, otherwise null
     */
    fun getDeckByID(deckId: String): Deck?

    fun getCardsOfDeck(deckId: String): List<Card>

    fun fillDeck(deckId: String): Boolean

    fun createDeck(deck: Deck): Boolean

    fun updateDeck(deck: Deck): Boolean

    fun deleteDeck(deckId: String): Boolean

    fun commitDecks(): Boolean

    fun getAll(): List<Deck>

    fun getAllInMemory(): List<Deck>
}