package com.br.doglove.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.br.doglove.R
import com.br.doglove.ktx.obtainViewModel
import com.br.doglove.model.Contacts
import com.br.doglove.model.Pets
import com.br.doglove.ui.adapter.ContactsAdapter
import com.br.doglove.ui.adapter.PetsAdapter
import com.br.doglove.viewmodel.ContactViewModel
import com.br.doglove.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.contact_fragment.view.*
import kotlinx.android.synthetic.main.home_favorites.*
import kotlinx.android.synthetic.main.home_favorites.view.*
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ContactFragment : Fragment() {

    private val contactsAdapter by lazy {
        ContactsAdapter()
    }

    @Inject
    @VisibleForTesting
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = obtainViewModel(viewModelFactory, ContactViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.contact_fragment, container, false)
        v.contacts_recycler.adapter = contactsAdapter
        subscribeToList()
        return v

    }

    private fun subscribeToList() {
        viewModel.contacts.observe(this, Observer<List<Contacts>> { it ->
            contactsAdapter.addContacts(it)
        })
    }


}
