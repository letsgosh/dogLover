package com.br.doglove.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.br.doglove.R
import com.br.doglove.ktx.obtainViewModel
import com.br.doglove.viewmodel.SplashViewModel
import dagger.android.AndroidInjection
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import java.util.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    @VisibleForTesting
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AndroidInjection.inject(this)
        viewModel = obtainViewModel(viewModelFactory, SplashViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.user.observe(this, Observer<Boolean> { user ->
            Timer().scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    if (!user) {
                        startActivity(intentFor<LoginActivity>().clearTop().clearTask().newTask())
                        cancel()
                    } else {
                        startActivity(intentFor<MainActivity>().clearTop().clearTask().newTask())
                        cancel()
                    }
                }
            }, 5000, 5000)
        })
    }
}
