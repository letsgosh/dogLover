package com.br.doglove.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
data class PetsDTO(
        @PrimaryKey val petsId: String,
        val name: String,
        val image: String)