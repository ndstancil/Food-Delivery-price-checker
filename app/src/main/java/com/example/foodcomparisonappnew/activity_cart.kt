package com.example.foodcomparisonappnew

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val cartItemsTextView = findViewById<TextView>(R.id.cartItemsTextView)
        val clearCartButton = findViewById<Button>(R.id.clearCartButton)

        // Display cart items
        val cartItems = Cart.getItems()
        if (cartItems.isNotEmpty()) {
            cartItemsTextView.text = cartItems.joinToString("\n")
        } else {
            cartItemsTextView.text = "Your Cart is Empty"
        }

        // Clear the cart when the button is clicked
        clearCartButton.setOnClickListener {
            Cart.clearCart()
            cartItemsTextView.text = "Your Cart is Empty"
        }
    }
}
