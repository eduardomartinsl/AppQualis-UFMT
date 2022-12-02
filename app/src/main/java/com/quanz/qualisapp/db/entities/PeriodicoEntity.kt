package com.quanz.qualisapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Cada tabela representa uma entidade, novas tabelas serao criadas a partir de novas entidades
conforme exemplo:
 */

@Entity(tableName = "periodico")
data class PeriodicoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val issn: String,

    val nome: String,

    val extratoCapes: String
)
