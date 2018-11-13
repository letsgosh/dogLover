package com.br.doglove.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.doglove.sharedpreferences.Prefs
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth) : ViewModel() {

    val user = MutableLiveData<Boolean>()
}