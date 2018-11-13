package com.br.doglove.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.doglove.sharedpreferences.Prefs
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class SplashViewModel
@Inject constructor
(
        private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    val user = MutableLiveData<Boolean>()
    init {
        user.value = firebaseAuth.currentUser != null
    }
}