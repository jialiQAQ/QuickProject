package com.alex.quickapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.alex.quickapp.model.ScannedSn
import java.util.*

class ScannedSnRepository private constructor(private val mDao: ScannedSnDao) {
    companion object {
        private var mInstance: ScannedSnRepository? = null
        fun getInstance(): ScannedSnRepository {
            mInstance ?: synchronized(this) {
                mInstance ?: ScannedSnRepository(AppDatabase.getInstance().getScannedSnDao()).also {
                    mInstance = it
                }
            }
            return mInstance!!
        }
    }

    suspend fun insertSn(ticketKey: String, sn: String) {
        mDao.insertSn(ScannedSn(UUID.randomUUID().toString(), ticketKey, sn))
    }

    suspend fun deleteSnByKey(key:String) {
        mDao.deleteSnByKey(key)
    }

    fun querySns(ticketKey: String): LiveData<MutableList<ScannedSn>> {
        return mDao.querySns(ticketKey)
    }
}