package com.example.vocalist.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vocalist.data.Vocalist

@Database(entities = [Vocalist::class], version = 2, exportSchema = false)
abstract class VocalistDb: RoomDatabase() {
    abstract fun favoriteVocalistDao(): DataDao

    companion object {
        @Volatile
        private var INSTANCE: VocalistDb? = null

        @JvmStatic
        fun getDb(context: Context): VocalistDb {
            if (INSTANCE == null) {
                synchronized(VocalistDb::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        VocalistDb::class.java, "favorite_database"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE as VocalistDb
        }
    }
}