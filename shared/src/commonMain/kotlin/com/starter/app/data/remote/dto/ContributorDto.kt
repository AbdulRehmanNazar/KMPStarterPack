package com.starter.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object for Remote APIs
 */
@Serializable
data class ContributorDto(
    val login: String,
    @SerialName("avatar_url") val avatarUrl: String,
    val contributions: Int
)