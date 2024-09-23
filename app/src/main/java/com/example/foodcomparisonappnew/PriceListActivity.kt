package com.example.foodcomparisonappnew

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PriceListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price_list)

        // Retrieve the store name
        val storeName = intent.getStringExtra("storeName")
        val storeNameTextView = findViewById<TextView>(R.id.storeNameTextView)
        storeNameTextView.text = storeName

        // Display prices based on the store name
        val pricesTextView = findViewById<TextView>(R.id.pricesTextView)
        pricesTextView.text = getSamplePrices(storeName)
    }



    private fun getSamplePrices(storeName: String?): String {
        return when (storeName) {
            "Pizza Hut" -> getPizzaHutMenu()
            "McDonald's" -> getMcdonaldsMenu()
            "Burger King" -> getBurgerKingMenu()
            else -> "Prices not available"
        }
    }

    // List of store items


    // Menu for McDonald's
    private fun getMcdonaldsMenu(): String {
        val items = listOf(
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
            "",
            "",
            "",
            "",
            "",
            "",
            "",

        )
        return "McDonald's Prices:\n" + items.joinToString("\n")
    }

    // Menu for Pizza Hut
    private fun getPizzaHutMenu(): String {
        val items = listOf(
            "Cheese Pizza: $12.99",
            "Pepperoni Pizza: $14.99",
            "Supreme Pizza: $17.99",
            "Breadsticks: $4.99",
            "Wings: $9.99"
        )
        return "Pizza Hut Prices:\n" + items.joinToString("\n")
    }

    // Menu for Burger King
    private fun getBurgerKingMenu(): String {
        val items = listOf(
            "Whopper: $6.99",
            "Chicken Sandwich: $7.49",
            "Fries (Small): $2.19",
            "Fries (Medium): $2.79",
            "Fries (Large): $3.19"
        )
        return "Burger King Prices:\n" + items.joinToString("\n")
    }
}
