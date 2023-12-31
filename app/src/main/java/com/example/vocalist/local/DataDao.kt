package com.example.vocalist.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.vocalist.data.Vocalist

@Dao
interface DataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteVocalist: Vocalist)

    @Delete
    suspend fun delete(favoriteVocalist: Vocalist)

    @Update
    suspend fun update(favoriteVocalist: Vocalist)

    @Query("SELECT * FROM Vocalist")
    suspend fun getAllVocalist(): List<Vocalist>

    @Query("SELECT EXISTS(SELECT * FROM Vocalist WHERE vocalist.id = :vocalistId)")
    fun isFavorite(vocalistId: Long): Boolean
}