package com.starter.app.core.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


fun androidDatabaseBuilder(context: Context): RoomDatabase.Builder<RoomSampleDataBase> {
    val dbFile = context.getDatabasePath(RoomSampleDataBase.DB_NAME)
    return Room.databaseBuilder(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
}