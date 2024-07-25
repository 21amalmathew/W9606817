package uk.ac.tees.mad.W9606817.Data.Favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavoriteDao {


    @Query("SELECT * FROM favorite_quotes")
    fun getAllFavorites () : List<FavoriteQuotes>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite (favoriteQuotes: FavoriteQuotes)

    @Query("Select * from favorite_quotes WHERE content  LIKE '%' || :content || '%'")
    suspend fun searchFavorites(content : String) : List<FavoriteQuotes>
}