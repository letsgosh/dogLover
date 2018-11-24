package com.br.doglove.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.doglove.R
import com.br.doglove.model.Contacts
import com.br.doglove.model.Pets
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_contacts.view.*
import kotlinx.android.synthetic.main.item_pets.view.*

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    private val contacts = mutableListOf<Contacts>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contacts, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(contacts[position])

    fun addContacts(contacts: List<Contacts>) {
        val positionStart = contacts.size.plus(1)
        this.contacts.addAll(contacts)
        notifyItemRangeChanged(positionStart, contacts.size)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(contacts: Contacts) {
            itemView.apply {
                name.text = contacts.name
                whatts.text = contacts.whatts
                phone.text = contacts.phone
                site.text = contacts.site

                Glide.with(itemView.context)
                        .load(contacts.image)
                        .apply(RequestOptions().placeholder(android.R.drawable.ic_menu_report_image))
                        .into(logo_contact)

                setOnClickListener {
                }
            }
        }
    }
}