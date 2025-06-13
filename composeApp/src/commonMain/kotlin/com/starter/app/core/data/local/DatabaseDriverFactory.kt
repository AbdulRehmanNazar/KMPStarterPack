package com.starter.app.core.data.local

import app.cash.sqldelight.db.SqlDriver

/**
 * Abstraction to get the starter of SQL according to platform like Android/Ios
 */
interface DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

/**
 * SQlDelight DataBase Driver according to platform specific
 */
expect class DataBaseDriver : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver
}