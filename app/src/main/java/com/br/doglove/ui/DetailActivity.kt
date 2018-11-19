package com.br.doglove.ui

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.br.doglove.R
import com.br.doglove.commons.BaseActivity
import com.br.doglove.ktx.obtainViewModel
import com.br.doglove.model.Pets
import com.br.doglove.viewmodel.DetailViewModel
import com.br.doglove.viewmodel.SplashViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

const val KEY_ACTIVITY_ARGS = "KEY_ACTIVITY_ARGS"

class DetailActivity : BaseActivity() {

//    @Inject
//    @VisibleForTesting
//    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

//        AndroidInjection.inject(this)
//        viewModel = obtainViewModel(viewModelFactory, DetailViewModel::class.java)


        if (savedInstanceState == null) {
            addFragment()
        }
    }

    private fun addFragment() {
        val args = intent.getParcelableExtra<Pets>(KEY_ACTIVITY_ARGS)
        val fragment = DetailFragment.newInstance(args)
        supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment, fragment.javaClass.canonicalName)
                .commit()
    }
}
