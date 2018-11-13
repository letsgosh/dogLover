package com.br.doglove.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
import java.math.BigDecimal

class Prefs(context: Context) {
    companion object {
        const val PREFS_FILENAME = "br.com.gruposinosserra.prefs"
        const val IS_USER = "is_user"
    }

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var isUser: Boolean
        get() = prefs.getBoolean(IS_USER, false)
        set(value) = prefs.edit().putBoolean(IS_USER, value).apply()


}
