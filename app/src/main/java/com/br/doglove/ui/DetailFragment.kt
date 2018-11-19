package com.br.doglove.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.br.doglove.R
import com.br.doglove.ktx.obtainViewModel
import com.br.doglove.model.Pets
import com.br.doglove.viewmodel.DetailViewModel
import com.br.doglove.viewmodel.FavoritesViewModel
import javax.inject.Inject

const val KEY_ARGS = "KEY_ARGS"

class DetailFragment : Fragment() {

//    @Inject
//    @VisibleForTesting
//    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailViewModel

    companion object {
        fun newInstance(detailsArgs: Pets): DetailFragment {
            val args = Bundle().apply { putParcelable(KEY_ARGS, detailsArgs) }
            return DetailFragment().apply { arguments = args }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.favorites_fragment, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = obtainViewModel(viewModelFactory, DetailViewModel::class.java)

//        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        binding.srlDetailsFav.setOnRefreshListener { viewModel.uiEvents.updateSwipes.accept(Unit) }
//        viewData.refreshEnabled = arguments!!.getParcelable<DetailsArgs>(KEY_ARGS).fromFavList
//        viewModel.state.observe(this, Observer<DetailsState> { it ->
//            it?.let { render(it) }
//        })
    }

    fun onPosterLoaded() {
        ActivityCompat.startPostponedEnterTransition(activity!!)
    }
}