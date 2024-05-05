package com.example.phonelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.phonelist.databinding.PeopleListBinding


class PhoneListAdapter(private var listPeople: List<Item>):
    RecyclerView.Adapter<PhoneListAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: PeopleListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = PeopleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listPeople.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val order = listPeople[position]
        with(holder.binding){
            titleName.text = order.name.title

            //textViewName.text = order.name.first + order.name.last

            textViewGender.text = order.gender

            textViewPhoneNumber.text = order.phone

            textViewEmail.text = order.email

            Glide.with(holder.binding.root)
                .load(order.picture.large)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
    }

}