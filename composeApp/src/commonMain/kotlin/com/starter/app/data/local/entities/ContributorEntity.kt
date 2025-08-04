package com.starter.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contributor")
data class ContributorEntity(
    @PrimaryKey
    var login: String = "",
    var avatarUrl: String = "",
    var contributions: Int = 0,
)