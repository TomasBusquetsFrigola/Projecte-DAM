package com.tomasbusfri.files.casinopark.Model.DAO.Implementations/*
package com.tomasbusfri.files.casinopark.Model.DAO.Implementations

import com.tomasbusfri.files.casinopark.Model.DAO.Interfaces.DAODeck
import com.tomasbusfri.files.casinopark.Model.DataClasses.Card
import com.tomasbusfri.files.casinopark.Model.DataClasses.CardColor
import com.tomasbusfri.files.casinopark.Model.DataClasses.Deck
import java.sql.Connection

class DAODeckImpl (
    val connection: Connection,
    val decksInMemory: MutableList<Deck> = mutableListOf(),
): DAODeck {
    override fun getDeckByID(deckId: String): Deck? {
        val sqlGet = "SELECT * FROM decks WHERE deck_id = ?"
        val preparedStatement = connection.prepareStatement(sqlGet)
        preparedStatement.setString(1, deckId)
        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            val cards = getCardsOfDeck(deckId)
            val returned = Deck(
                deckId = resultSet.getString("deck_id"),
                nCards = resultSet.getInt("nCards"),
                cards = cards
            )
            preparedStatement.close()
            resultSet.close()
            return returned
        } else {
            resultSet.close()
            preparedStatement.close()
            return null
        }
    }

    override fun getCardsOfDeck(deckId: String): List<Card> {
        val cards = mutableListOf<Card>()
        val sglGet = "SELECT * FROM cards WHERE deck_id = ?"
        val preparedStatement = connection.prepareStatement(sglGet)
        preparedStatement.setString(1, deckId)
        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            (0..resultSet.fetchSize).forEach {
                cards.add(
                    Card(
                        cardValue = resultSet.getInt("card_value"),
                        cardSuit = resultSet.getString("card_suit"),
                        cardSide = resultSet.getString("card_side"),
                        cardColor =
                            if (resultSet.getString("card_color") == "black") CardColor.BLACK
                            else CardColor.RED
                    )
                )
            }
            resultSet.close()
            preparedStatement.close()
            return cards.toList()
        } else {
            resultSet.close()
            preparedStatement.close()
            return cards.toList()
        }
    }

    override fun fillDeck(deckId: String): Boolean {
        val cards = getCardsOfDeck(deckId)
        if (cards.isNotEmpty()) {
            val index = decksInMemory.indexOfFirst {
                it.deckId == deckId
            }
            decksInMemory[index] = decksInMemory[index].copy(cards = cards)
            return true
        } else {
            return false
        }
    }

    override fun createDeck(deck: Deck): Boolean {
        val sqlCreate = "INSET INTO decks (deck_id, nCards) VALUES (?,?)"
        val preparedStatement = connection.prepareStatement(sqlCreate)
        preparedStatement.setString(1, deck.deckId)
        preparedStatement.setInt(2, deck.nCards)
        val resultSet = preparedStatement.executeUpdate()
        if (resultSet > 0) {
            preparedStatement.close()
            return true
        } else {
            preparedStatement.close()
            return false
        }
    }

    override fun updateDeck(deck: Deck): Boolean {
        val sqlUpdate = "UPDATE decks SET nCards = ? WHERE id = ?"
        val preparedStatement = connection.prepareStatement(sqlUpdate)
        preparedStatement.setInt(1, deck.nCards)
        preparedStatement.setString(2, deck.deckId)
        val resultSet = preparedStatement.executeUpdate()
        if (resultSet > 0) {
            preparedStatement.close()
            return true
        } else {
            preparedStatement.close()
            return false
        }
    }

    override fun deleteDeck(deckId: String): Boolean {
        val sqlDelete = "DELETE FROM decks WHERE deck_id = ?"
        val preparedStatement = connection.prepareStatement(sqlDelete)
        preparedStatement.setString(1, deckId)
        val resultSet = preparedStatement.executeUpdate()
        if (resultSet > 0) {
            preparedStatement.close()
            return true
        } else {
            preparedStatement.close()
            return false
        }
    }

    override fun commitDecks(): Boolean {
        val sqlMassUpdate = "UPDATE decks SET nCards = ? WHERE id = ?"
        val preparedStatement = connection.prepareStatement(sqlMassUpdate)
        (0..<decksInMemory.size).forEach { index ->
            preparedStatement.setInt(1, decksInMemory[index].nCards)
            preparedStatement.setString(2, decksInMemory[index].deckId)
            val result = preparedStatement.executeUpdate()
            if (result == 0) return false
        }
        return true
    }

    override fun getAll(): List<Deck> {
        val decks = mutableListOf<Deck>()
        val sqlGet = "SELECT * FROM decks"
        val preparedStatement = connection.prepareStatement(sqlGet)
        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            (0..resultSet.fetchSize).forEach {
                val cards= getCardsOfDeck(resultSet.getString("deck_id"))
                decks.add(
                    Deck(
                        deckId = resultSet.getString("deck_id"),
                        nCards = resultSet.getInt("nCards"),
                        cards = cards
                    )
                )
            }
            resultSet.close()
            preparedStatement.close()
            return decks.toList()
        } else {
            resultSet.close()
            preparedStatement.close()
            return decks.toList()
        }
    }

    override fun getAllInMemory(): List<Deck> {
        return decksInMemory.toList()
    }
}*/
