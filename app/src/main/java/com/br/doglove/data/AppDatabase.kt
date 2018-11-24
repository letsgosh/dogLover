package com.br.doglove.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.br.doglove.data.dto.NotificationPetsDTO
import com.br.doglove.data.dto.PetsDTO

@Database(entities = [PetsDTO::class, NotificationPetsDTO::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun petsDao(): PetsDao
    abstract fun notificationDao(): NotificationDao
}