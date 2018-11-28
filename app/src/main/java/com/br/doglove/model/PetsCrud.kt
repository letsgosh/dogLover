package com.br.doglove.model

import android.os.Parcelable
import com.google.type.LatLng
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize



data class PetsCrud(
        var name: String,
        val year: String,
        val description: String,
        val port: String,
        val type: String,
        val latLng: LatLng,
        val image: List<String>
)

