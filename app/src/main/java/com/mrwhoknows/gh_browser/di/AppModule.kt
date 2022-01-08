package com.mrwhoknows.gh_browser.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mrwhoknows.gh_browser.data.GithubBrowserDB
import com.mrwhoknows.gh_browser.data.RepositoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GithubBrowserDB =
        Room.databaseBuilder(
            context,
            GithubBrowserDB::class.java, "github_browser"
        ).build()

    @Provides
    @Singleton
    fun provideRepositoryDao(githubBrowserDB: GithubBrowserDB): RepositoryDao =
        githubBrowserDB.repositoryDao()
}