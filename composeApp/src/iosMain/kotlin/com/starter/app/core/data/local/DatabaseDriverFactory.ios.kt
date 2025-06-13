package com.starter.app.core.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.starter.app.db.AppDatabase

actual class DataBaseDriver : DatabaseDriverFactory {
    actual override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "starter.db")
    }
}