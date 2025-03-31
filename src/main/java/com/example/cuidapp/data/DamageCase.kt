package com.example.cuidapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DamageCase::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun damageCaseDao(): DamageCaseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "damage_cases_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }


}
