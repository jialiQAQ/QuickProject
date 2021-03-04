package com.alex.quickapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alex.quickapp.model.ScannedSn

@Dao
interface ScannedSnDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSn(scannedSn: ScannedSn): Long

    @Query("SELECT * FROM ticket_sns where ticket_key=:ticketKey order by sn")
    fun querySns(ticketKey: String): LiveData<MutableList<ScannedSn>>

    @Query("DELETE FROM ticket_sns where ticket_key=:ticket_key")
    suspend fun deleteSnByKey(ticket_key: String)
}