package com.br.doglove.ui

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.FragmentActivity
import com.br.doglove.R
import com.br.doglove.model.Pets
import com.br.doglove.model.PetsCrud
import com.google.type.LatLng
import org.jetbrains.anko.intentFor
import kotlinx.android.synthetic.main.activity_create_pet.*

class CreatePetActivity : FragmentActivity() {

    private lateinit var port: String
    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_pet)

        port_pet_detail.setOnCheckedChangeListener { _, checkedId ->
            port = (findViewById<RadioButton>(checkedId)).text.toString()
        }

        type_pet_detail.setOnCheckedChangeListener { _, checkedId ->
            type = (findViewById<RadioButton>(checkedId)).text.toString()
        }

    }

    fun nextPhotos(view: View) {
        PetsCrud(desc_crud_pet.text.toString(),
                desc_crud_pet.text.toString(),
                port,
                type,
                LatLng.getDefaultInstance(),
                listOf("https://firebasestorage.googleapis.com/v0/b/doglove-4907d.appspot.com/o/adote.jpg?alt=media&token=6efd3185-a671-4d04-8afa-2a3dbe983ed1"))

        startActivity(intentFor<PhotoActivity>())
    }
}
