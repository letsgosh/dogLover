package com.br.doglove.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Pets(
        val id: Int,
        val name: String,
        val year: String,
        val image: String
) : Parcelable