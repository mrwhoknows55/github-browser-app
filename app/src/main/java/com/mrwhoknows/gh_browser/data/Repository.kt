package com.mrwhoknows.gh_browser.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Repository(
    @PrimaryKey val id: Int,
    @ColumnInfo val repoUsername: String,
    @ColumnInfo val repoName: String
)
