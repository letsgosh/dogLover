package com.br.doglove.ktx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


fun <T : ViewModel> FragmentActivity.obtainViewModel(viewModelFactory: ViewModelProvider.Factory, viewModelClass: Class<T>) =
        ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)

fun <T : ViewModel> Fragment.obtainViewModel(viewModelFactory: ViewModelProvider.Factory, viewModelClass: Class<T>) =
        ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)