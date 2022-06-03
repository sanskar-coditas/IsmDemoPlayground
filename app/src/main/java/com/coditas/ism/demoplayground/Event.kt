package com.coditas.ism.demoplayground

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity (tableName = "event")
data class Event(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: String,
    val title: String
)
