package com.br.doglove.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Contacts(
        val id: String,
        val name: String,
        val whatts: String,
        val phone: String,
        val site: String,
        val image: String
) : Parcelable