package com.example.foodcomparisonappnew

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.foodcomparisonappnew.com.example.foodcomparisonappnew.Store
import com.example.foodcomparisonappnew.com.example.foodcomparisonappnew.StoreAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var storeAdapter: StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.storeListView)

        val stores = listOf(
            Store("Pizza Hut", R.drawable.pizza_hut),
            Store("McDonald's", R.drawable.mcdonalds),
            Store("Burger King", R.drawable.burgerking)
        )

        storeAdapter = StoreAdapter(this, stores)
        listView.adapter = storeAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedStore = stores[position]
            val intent = Intent(this, PriceListActivity::class.java)
            intent.putExtra("storeName", selectedStore.name)
            startActivity(intent)
        }
    }
}