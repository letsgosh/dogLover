package com.br.doglove.viewmodel

import androidx.annotation.Nullable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.doglove.model.PetsCrud
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import java.nio.file.Files.exists
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener


class DetailViewModel @Inject constructor(firestore: FirebaseFirestore) : ViewModel() {

    val petsDetail = MutableLiveData<PetsCrud>()

    fun detailPet(referece: String) {
        firestore.collection("petsDetail")
                .document(referece).addSnapshotListener(EventListener<DocumentSnapshot> { snapshot, _ ->
                    if (snapshot != null && snapshot.exists()) {
                        petsDetail.value = PetsCrud(snapshot.data!! as PetsCrud)
                    }
                })
    }

}
