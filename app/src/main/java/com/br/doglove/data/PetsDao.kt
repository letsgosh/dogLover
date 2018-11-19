package com.br.doglove.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.br.doglove.data.dto.PetsDTO
import com.br.doglove.model.Pets

@Dao
interface PetsDao {
    @Query("SELECT * FROM pets ORDER BY name")
    fun getPets(): LiveData<List<PetsDTO>>

    @Query("SELECT * FROM pets WHERE petsId = :petsId")
    fun getPet(petsId: String): LiveData<PetsDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(petsDTO: PetsDTO)
}
