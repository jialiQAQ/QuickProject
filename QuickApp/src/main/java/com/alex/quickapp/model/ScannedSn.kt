package com.alex.quickapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alex.quickapp.constant.SnStateConstant

@Entity(tableName = "ticket_sns")
class ScannedSn(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "ticket_key")
    val ticketKey: String,
    val sn: String,
    var state: String = SnStateConstant.NORMAL
) {
}