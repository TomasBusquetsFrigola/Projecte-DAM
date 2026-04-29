package com.tomasbusfri.files.casinopark.Model.DAO.Config

import com.tomasbusfri.files.casinopark.Model.DAO.Implementations.DAODeckImpl
import com.tomasbusfri.files.casinopark.Model.DAO.Interfaces.DAODeck
import kotlin.getValue

class DAOFactory {
    private val conn by lazy { ConnectionFactory().createConnection() }

    fun startDAO(): DAODeckImpl {
        return DAODeckImpl(conn)
    }
}