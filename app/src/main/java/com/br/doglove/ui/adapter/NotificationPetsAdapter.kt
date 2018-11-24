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
import com.br.doglove.data.dto.NotificationPetsDTO
import com.br.doglove.model.Pets
import com.br.doglove.ui.CreatePetActivity
import com.br.doglove.ui.DetailActivity
import com.br.doglove.ui.KEY_ACTIVITY_ARGS
import kotlinx.android.synthetic.main.item_notification.view.*
import kotlinx.android.synthetic.main.item_pets.view.*

class NotificationPetsAdapter : RecyclerView.Adapter<NotificationPetsAdapter.ViewHolder>() {
    private val notification = mutableListOf<NotificationPetsDTO>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = notification.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notification[position])

    fun addNotification(pets: List<NotificationPetsDTO>) {
        val positionStart = pets.size.plus(1)
        this.notification.addAll(pets)
        notifyItemRangeChanged(positionStart, pets.size)
    }

    fun clear(){
        notification.clear()
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(notificationPetsDTO: NotificationPetsDTO) {
            itemView.apply {
                notification_title.text = notificationPetsDTO.title
                notification_desc.text = notificationPetsDTO.description
                notification_date.text = notificationPetsDTO.date.toString()

                setOnClickListener {
                }
            }
        }
    }
}