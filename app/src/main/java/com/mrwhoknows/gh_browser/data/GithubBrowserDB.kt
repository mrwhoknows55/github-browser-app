package com.mrwhoknows.gh_browser.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Repository::class], version = 1)
abstract class GithubBrowserDB : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}