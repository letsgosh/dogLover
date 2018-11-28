package com.br.doglove.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.doglove.model.Contacts
import com.br.doglove.model.Pets
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class ContactViewModel @Inject constructor(firestore: FirebaseFirestore) : ViewModel() {

    val contacts = MutableLiveData<List<Contacts>>()

    init {
        firestore.collection("contacts")
                .get()
                .addOnCompleteListener { it ->
                    if (it.isSuccessful) {

                        var contactList = ArrayList<Contacts>()
                        for (snapshot in it.result!!.documents) {
                            contactList.add(Contacts(snapshot.id,
                                    snapshot.getString("name")!!,
                                    snapshot.getString("whatts")!!,
                                    snapshot.getString("email")!!,
                                    snapshot.getString("site")!!,
                                    snapshot.getString("image")!!
                            ))
                        }

                        contacts.value = contactList
                    } else {

                    }
                }
    }

}
