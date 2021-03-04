package com.alex.quickapp.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alex.quickapp.base.ScanApplication
import com.alex.quickapp.data.AppDatabase.Companion.DB_VERSION
import com.alex.quickapp.model.ScannedSn

@Database(entities = [ScannedSn::class], version = DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getScannedSnDao(): ScannedSnDao

    companion object {
        private const val DB_NAME = "product_scan_db"
        const val DB_VERSION = 1

        @Volatile
        private var mDb: AppDatabase? = null

        fun getInstance(): AppDatabase {
            mDb ?: synchronized(this) {
                mDb ?: Room.databaseBuilder(
                    ScanApplication.getInstance(),
                    AppDatabase::class.java,
                    DB_NAME
                ).build().also {
                    mDb = it
                }
            }
            return mDb!!
        }
    }


}