package com.wayne.assistant.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
abstract class WayneDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: WayneDatabase? = null

        fun getDatabase(context: Context): WayneDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WayneDatabase::class.java,
                    "wayne_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
