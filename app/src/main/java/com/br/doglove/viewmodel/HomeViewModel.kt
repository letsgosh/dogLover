package com.br.doglove.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.doglove.model.Pets
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class HomeViewModel @Inject constructor(firestore: FirebaseFirestore) : ViewModel() {

    val pets = MutableLiveData<List<Pets>>()

    init {
        firestore.collection("pets")
                .get()
                .addOnCompleteListener { it ->
                    if (it.isSuccessful) {
                        var petList = ArrayList<Pets>()
                        for (snapshot in it.result!!.documents) {
                           petList.add(Pets(snapshot.id,
                                    snapshot.getString("name")!!,
                                    snapshot.getString("year")!!,
                                    snapshot.getString("image")!!))
                            petList.add(Pets(snapshot.id,
                                    snapshot.getString("name")!!,
                                    snapshot.getString("year")!!,
                                    snapshot.getString("image")!!))
                            petList.add(Pets(snapshot.id,
                                    snapshot.getString("name")!!,
                                    snapshot.getString("year")!!,
                                    snapshot.getString("image")!!))
                            petList.add(Pets(snapshot.id,
                                    snapshot.getString("name")!!,
                                    snapshot.getString("year")!!,
                                    snapshot.getString("image")!!))
                            petList.add(Pets(snapshot.id,
                                    snapshot.getString("name")!!,
                                    snapshot.getString("year")!!,
                                    snapshot.getString("image")!!))
                            petList.add(Pets(snapshot.id,
                                    snapshot.getString("name")!!,
                                    snapshot.getString("year")!!,
                                    snapshot.getString("image")!!))
                            petList.add(Pets(snapshot.id,
                                    snapshot.getString("name")!!,
                                    snapshot.getString("year")!!,
                                    snapshot.getString("image")!!))
                        }
                        pets.value = petList
                    } else {

                    }
                }
    }


}