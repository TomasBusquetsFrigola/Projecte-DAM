package com.tomasbusfri.files.casinopark.Model.DAO.Implementations

import com.tomasbusfri.files.casinopark.Model.API.APIClasses.APICard
import com.tomasbusfri.files.casinopark.Model.DAO.Interfaces.DAODeck
import com.tomasbusfri.files.casinopark.Model.DataClasses.Card
import com.tomasbusfri.files.casinopark.Model.DataClasses.Deck
import com.tomasbusfri.files.casinopark.Model.DataClasses.toCard
import java.sql.Connection

class DAODeckImpl (
    val connection: Connection,
    val decksInMemory: MutableList<Deck> = mutableListOf(),
) {

}