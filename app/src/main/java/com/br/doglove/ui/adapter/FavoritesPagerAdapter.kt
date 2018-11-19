package com.br.doglove.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.br.doglove.R
import com.br.doglove.data.dto.PetsDTO
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoritesPagerAdapter(private val context: Context, private var pets: List<PetsDTO>) : PagerAdapter() {

    private var mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = mInflater.inflate(R.layout.item_favorite, container, false)
        view.name_pet_favorite.text = pets[position].name
        Glide.with(context)
                .load(pets[position].image)
                .apply(RequestOptions().placeholder(android.R.drawable.ic_menu_report_image))
                .into(view.pet_favorite_image)

        container.addView(view)
        return view

    }

    override fun getCount(): Int {
        return pets.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}