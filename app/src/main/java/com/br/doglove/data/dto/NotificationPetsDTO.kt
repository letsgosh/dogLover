package com.br.doglove.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notification")
data class NotificationPetsDTO(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val title: String,
        val description: String,
        val date: Date)