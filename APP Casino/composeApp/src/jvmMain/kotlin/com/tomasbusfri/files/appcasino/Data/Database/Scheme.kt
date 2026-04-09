package com.tomasbusfri.files.appcasino.Data.Database



object Decks: Name.Table("Cards") {
    val id = integer("deck_id").autoIncrement()
}