package com.br.doglove.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.br.doglove.data.dto.NotificationPetsDTO
import com.br.doglove.data.dto.PetsDTO
import com.br.doglove.model.Pets

@Dao
interface NotificationDao {
    @Query("SELECT * FROM notification ORDER BY id")
    fun getNotification(): LiveData<List<NotificationPetsDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notificationPetsDTO: NotificationPetsDTO)
}
