package com.br.doglove.data

class PetsRepository private constructor(private val petsDao: PetsDao) {

    fun getPets() = petsDao.getPets()

    fun getPet(petsId: String) = petsDao.getPet(petsId)
}
