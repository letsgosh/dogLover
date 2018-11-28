package com.br.doglove.ui

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
import com.br.doglove.ui.adapter.FavoritesPagerAdapter
import com.br.doglove.viewmodel.FavoritesViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.favorites_fragment.view.*
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    private lateinit var favoritesAdapter: FavoritesPagerAdapter

    @Inject
    @VisibleForTesting
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.favorites_fragment, container, false)
        v.pager_favorites.offscreenPageLimit = 3
        v.pager_favorites.pageMargin = 20
        return v

    }

    private fun subscribeToList() {

        viewModel.pets.observe(this, Observer { it ->
            favoritesAdapter = FavoritesPagerAdapter(this.context!!, it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = obtainViewModel(viewModelFactory, FavoritesViewModel::class.java)
        subscribeToList()
    }

}
