package com.br.doglove.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.br.doglove.R
import com.br.doglove.ktx.obtainViewModel
import com.br.doglove.model.Pets
import com.br.doglove.ui.adapter.PetsAdapter
import com.br.doglove.viewmodel.HomeViewModel

import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.home_favorites.*
import kotlinx.android.synthetic.main.home_favorites.view.*
import javax.inject.Inject


class HomeFragment : Fragment() {
    private val petsAdapter by lazy {
        PetsAdapter()
    }

    private var recyclerState: Parcelable? = null

    @Inject
    @VisibleForTesting
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = obtainViewModel(viewModelFactory, HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.home_favorites, container, false)
        v.pets_recycler.adapter = petsAdapter
        subscribeToList()
        return v

    }

    private fun subscribeToList() {
        viewModel.pets.observe(this, Observer<List<Pets>> { it ->
            petsAdapter.addPets(it)
            if (recyclerState != null) {
                pets_recycler.layoutManager?.onRestoreInstanceState(recyclerState)
                recyclerState = null
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable("lmState", pets_recycler.layoutManager?.onSaveInstanceState())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        recyclerState = savedInstanceState?.getParcelable("lmState")
    }
}
