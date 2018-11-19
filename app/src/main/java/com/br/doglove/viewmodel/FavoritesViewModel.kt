package com.br.doglove.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.doglove.data.AppDatabase
import com.br.doglove.data.PetsRepository
import com.br.doglove.model.Pets
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(appDatabase: AppDatabase) : ViewModel() {

    val pets = appDatabase.petsDao().getPets()

}
