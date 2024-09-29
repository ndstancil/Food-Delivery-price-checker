package com.example.foodcomparisonappnew

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PriceListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price_list)

        // Retrieve the store name
        val storeName = intent.getStringExtra("storeName")
        val storeNameTextView = findViewById<TextView>(R.id.storeNameTextView)
        storeNameTextView.text = storeName

        // Get the list of items based on the store name
        val items = getSamplePrices(storeName)

        // the adapter and set it to the ListView
        val itemListView = findViewById<ListView>(R.id.itemListView)
        val itemAdapter = ItemAdapter(this, items)
        itemListView.adapter = itemAdapter
    }

    // list of items for stores
    private fun getSamplePrices(storeName: String?): List<String> {
        return when (storeName) {
            "Pizza Hut" -> listOf(
                "Cheese Pizza: $12.99",
                "Pepperoni Pizza: $14.99",
                "Supreme Pizza: $17.99",
                "Breadsticks: $4.99",
                "Wings: $9.99"
            )
            "McDonald's" -> listOf(
                "French Fries: $2.89",
                "10 pc. Chicken McNuggets: $8.99",
                "10 pc. Chicken McNuggets: $6.39",
                "McChicken: $3.24",
                "Triple Cheeseburger Meal: $8.69",
                "Double Cheeseburger: $3.99",
                "2 Cheeseburger Meal: $9.29",
                "Big Mac Meal: $9.99",
                "Strawberry & Crème Pie: $2.59",
                "Classic Big Mac Pack: $26.99",
                "6 pc. Chicken McNuggets: $3.99",
                "",
                "",
                "",
                "",
                "",
                "",

                )
            "Burger King" -> listOf(
                "Whopper: $6.99",
                "Chicken Sandwich: $7.49",
                "Fries (Small): $2.19",
                "Fries (Medium): $2.79",
                "Fries (Large): $3.19"
            )
            else -> emptyList()
        }
    }
}
