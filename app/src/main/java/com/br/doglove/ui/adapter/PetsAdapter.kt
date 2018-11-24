package com.br.doglove.ui.adapter

import android.content.Intent
import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.br.doglove.R
import com.br.doglove.R.id.name
import com.br.doglove.model.Pets
import com.br.doglove.ui.CreatePetActivity
import com.br.doglove.ui.DetailActivity
import com.br.doglove.ui.KEY_ACTIVITY_ARGS
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_pets.view.*

class PetsAdapter : RecyclerView.Adapter<PetsAdapter.ViewHolder>() {
    private val pets = mutableListOf<Pets>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pets, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = pets.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(pets[position])

    fun addPets(pets: List<Pets>) {
        val positionStart = pets.size.plus(1)
        this.pets.addAll(pets)
        notifyItemRangeChanged(positionStart, pets.size)
    }

    fun clear(){
        pets.clear()
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pets: Pets) {
            itemView.apply {
                name_pet.text = pets.name
                year_pet.text = pets.year

                Glide.with(itemView.context)
                        .load(pets.image)
                        .apply(RequestOptions().placeholder(android.R.drawable.ic_menu_report_image))
                        .into(image_pet)

                setOnClickListener {
                }
            }
        }
    }
}