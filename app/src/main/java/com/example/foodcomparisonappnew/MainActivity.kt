package com.example.foodcomparisonappnew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        // CardView for the stores
        val pizzaHutCard = findViewById<CardView>(R.id.pizzaHutCard)
        val mcdonaldsCard = findViewById<CardView>(R.id.mcdonaldsCard)
        val burgerKingCard = findViewById<CardView>(R.id.burgerKingCard)

        // click listeners for each CardView
        pizzaHutCard.setOnClickListener {
            openPriceList("Pizza Hut", R.drawable.pizza_hut)
        }

        mcdonaldsCard.setOnClickListener {
            openPriceList("McDonald's", R.drawable.mcdonalds)
        }

        burgerKingCard.setOnClickListener {
            openPriceList("Burger King", R.drawable.burgerking)
        }

        // Handle Cart button click
        findViewById<Button>(R.id.viewCartButton).setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    // open PriceListActivity with store name and logo
    private fun openPriceList(storeName: String, storeLogoResId: Int) {
        val intent = Intent(this, PriceListActivity::class.java)
        intent.putExtra("storeName", storeName)
        intent.putExtra("storeLogo", storeLogoResId)
        startActivity(intent)
    }
}