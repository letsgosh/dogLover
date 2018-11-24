package com.br.doglove.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.transaction
import com.br.doglove.R
import com.br.doglove.commons.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, CreatePetActivity::class.java))
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter_port -> {
                showModalPort()
                true
            }
            R.id.action_filter_type -> {
                showModalType()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager.transaction(allowStateLoss = true) {
                    replace(R.id.content, HomeFragment())
                }
            }
            R.id.nav_favorites -> {
                supportFragmentManager.transaction(allowStateLoss = true) {
                    replace(R.id.content, FavoritesFragment())
                }
            }
            R.id.nav_notification -> {
                supportFragmentManager.transaction(allowStateLoss = true) {
                    replace(R.id.content, NotificationFragment())
                }
            }
            R.id.nav_contacts -> {
                supportFragmentManager.transaction(allowStateLoss = true) {
                    replace(R.id.content, ContactFragment())
                }
            }
            R.id.nav_about -> {
                supportFragmentManager.transaction(allowStateLoss = true) {
                    replace(R.id.content, AboutFragment())
                }
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showModalPort() {
        val portsChecked = booleanArrayOf(true, true, true, true)
        AlertDialog.Builder(this)
                .setTitle(R.string.action_settings)
                .setMultiChoiceItems(R.array.filter_port, null) { _, which, isChecked ->
                    portsChecked[which] = isChecked
                }
                .setPositiveButton(R.string.filter) { _, _ ->
                    //TODO FILTER
                }
                .setNegativeButton(R.string.cancel) { _, _ ->
                    //TODO CANCEL
                }.create().show()
    }

    private fun showModalType() {
        val typeChecked = booleanArrayOf(true, true, true)
        AlertDialog.Builder(this)
                .setTitle(R.string.action_settings)
                .setMultiChoiceItems(R.array.filter_type, null) { _, which, isChecked ->
                    typeChecked[which] = isChecked
                }
                .setPositiveButton(R.string.filter) { _, _ ->
                    //TODO FILTER
                }
                .setNegativeButton(R.string.cancel) { _, _ ->
                    //TODO CANCEL
                }.create().show()
    }
}
