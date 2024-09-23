package com.example.foodcomparisonappnew.com.example.foodcomparisonappnew

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.foodcomparisonappnew.R

class StoreAdapter(
    context: Context,
    private val stores: List<Store>
) : ArrayAdapter<Store>(context, 0, stores) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item_store, parent, false)

        val currentStore = stores[position]

        val imageView = itemView.findViewById<ImageView>(R.id.storeImage)
        imageView.setImageResource(currentStore.imageResource)

        val nameTextView = itemView.findViewById<TextView>(R.id.storeName)
        nameTextView.text = currentStore.name

        return itemView
    }
}
