package com.starter.app.core.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.starter.app.db.AppDatabase

/**
 * SqlDelight Database Driver for Android
 */
actual class DataBaseDriver(private val context: Context) : DatabaseDriverFactory {
    actual override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            AppDatabase.Schema,
            context, "starter.db"
        )
    }
}