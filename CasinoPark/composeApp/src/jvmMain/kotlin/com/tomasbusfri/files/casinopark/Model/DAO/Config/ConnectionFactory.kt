package com.tomasbusfri.files.casinopark.Model.DAO.Config

import java.sql.Connection
import java.sql.DriverManager

class ConnectionFactory {
    private val URL = "jdbc:sqlite:casinopark.db"

    fun createConnection(): Connection {
        return DriverManager.getConnection(URL)
    }
}