package com.br.doglove.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.core.view.isVisible

import com.br.doglove.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    fun openTerms(view: View) {
        webview_about.loadUrl(view.tag.toString())
        webview_about.settings.javaScriptEnabled = true
        toogle(true)
    }

    fun toogle(open: Boolean){
        if(open) {
            terms.visibility = View.GONE
            view_about.visibility = View.GONE
            letsgo.visibility = View.GONE
            about.visibility = View.GONE
            webview_about.visibility = View.VISIBLE
        } else {
            terms.visibility = View.VISIBLE
            view_about.visibility = View.VISIBLE
            letsgo.visibility = View.VISIBLE
            about.visibility = View.VISIBLE
            webview_about.visibility = View.GONE
        }
    }

}
