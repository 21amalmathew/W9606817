package uk.ac.tees.mad.W9606817.Data.Local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quotes")
data class Quote(
    @PrimaryKey val id: String,
    val content: String,
    val author: String,
    val date: String
)