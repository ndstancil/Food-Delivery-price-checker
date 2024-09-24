package com.example.foodcomparisonappnew

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ItemAdapter(context: Context, private val items: List<String>) :
    ArrayAdapter<String>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        val currentItem = items[position]

        val itemNameTextView = itemView.findViewById<TextView>(R.id.itemNameTextView)
        val addToCartButton = itemView.findViewById<Button>(R.id.addToCartButton)

        itemNameTextView.text = currentItem

        addToCartButton.setOnClickListener {
            Cart.addItem(currentItem)
            Toast.makeText(context, "$currentItem added to cart", Toast.LENGTH_SHORT).show()
        }

        return itemView
    }
}