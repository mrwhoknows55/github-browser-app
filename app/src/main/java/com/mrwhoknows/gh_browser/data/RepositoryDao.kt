package com.mrwhoknows.gh_browser.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM Repository")
    suspend fun getAllRepositories(): List<Repository>

    @Query("SELECT * FROM Repository WHERE id=:id")
    suspend fun getAllRepositoryById(id: Int): List<Repository>

    @Insert
    suspend fun insertRepository(repository: Repository)

    @Delete
    suspend fun deleteRepository(repository: Repository)

    @Query("DELETE FROM Repository WHERE id=:id")
    suspend fun deleteRepository(id: Int)
}