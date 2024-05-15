package com.example.converter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.converter.databinding.CurrenciesBinding

class ValutsListAdapter(private var listValuts: List<Item>, private val listener: (item: Item) -> Unit):
    RecyclerView.Adapter<ValutsListAdapter.ItemViewHolder>() {

        class ItemViewHolder(val binding: CurrenciesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = CurrenciesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listValuts.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val order = listValuts[position]
        with(holder.binding){
            if(order.name != "TJS") {
                valute.text = order.name

                nameOfValute.text = order.fullName

                valueOfValute.text = String.format("%.6f", order.value.toDouble() / order.nominal.toDouble()) + " TJS"

                Glide.with(holder.binding.root)
                    .load(order.flag)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(flag)

                root.setOnClickListener {
                    listener(order)
                }
            }
        }
    }

}