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
import com.br.doglove.ui.adapter.FavoritesPagerAdapter
import com.br.doglove.ui.adapter.NotificationPetsAdapter
import com.br.doglove.viewmodel.FavoritesViewModel
import com.br.doglove.viewmodel.NotificationViewModel
import kotlinx.android.synthetic.main.home_favorites.view.*
import kotlinx.android.synthetic.main.notification_fragment.view.*
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NotificationFragment : Fragment() {
    private val notificationAdapter by lazy {
        NotificationPetsAdapter()
    }

    @Inject
    @VisibleForTesting
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NotificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.notification_fragment, container, false)
        v.notification_dog_love.adapter = notificationAdapter
        return v
    }

    private fun subscribeToList() {

        viewModel.notification.observe(this, Observer { it ->
            notificationAdapter.addNotification(it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = obtainViewModel(viewModelFactory, NotificationViewModel::class.java)
        subscribeToList()
    }

}
