package com.tomasbusfri.files.casinopark.Model.DAO.Implementations

import com.tomasbusfri.files.casinopark.Model.API.APIClasses.APICard
import com.tomasbusfri.files.casinopark.Model.DAO.Interfaces.DAODeck
import com.tomasbusfri.files.casinopark.Model.DataClasses.Card
import com.tomasbusfri.files.casinopark.Model.DataClasses.Deck
import com.tomasbusfri.files.casinopark.Model.DataClasses.toCard

class DAODeckImpl: DAODeck {
    override fun getDeckByID(deckId: Int): Deck? {
        TODO("Not yet implemented")
    }

    override fun getCardsOfDeck(deckId: Int): List<Card> {
        TODO("Not yet implemented")
    }

    override fun fillDeck(APICard: APICard): Boolean {
        val cardsToCards = APICard.cards.forEach { card ->
            card.toCard()
        }
        return false
    }

    override fun createDeck(deck: Deck): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateDeck(deck: Deck): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteDeck(deck: Deck): Boolean {
        TODO("Not yet implemented")
    }

    override fun commitDecks(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<Deck> {
        TODO("Not yet implemented")
    }
}