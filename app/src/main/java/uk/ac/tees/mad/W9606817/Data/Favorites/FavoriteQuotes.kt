package uk.ac.tees.mad.W9606817.Data.Favorites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_quotes")
data class FavoriteQuotes(
    @PrimaryKey(autoGenerate = true) val quoteid: Int = 0,
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val deviceDate : String
)
