package com.br.doglove.model

import android.os.Parcelable
import com.google.type.LatLng
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


data class PetsCrud(
        val description: String,
        val humor: String,
        val port: String,
        val type: String,
        val latLng: LatLng,
        val image: List<String>
) {
    constructor(petsCrud: PetsCrud) : this(petsCrud.description, petsCrud.humor,
            petsCrud.port, petsCrud.type, petsCrud.latLng, petsCrud.image)
}

