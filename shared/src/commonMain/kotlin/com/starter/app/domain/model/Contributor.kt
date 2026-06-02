package com.starter.app.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Domain model for Contributer
 */
@Serializable
data class Contributor(
    val login: String,
    @SerialName("avatar_url") val avatarUrl: String,
    val contributions: Int
)