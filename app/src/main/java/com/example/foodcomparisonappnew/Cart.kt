package com.example.foodcomparisonappnew

object Cart {
    private val cartItems = mutableListOf<String>()

    // Method to add an item to the cart
    fun addItem(item: String) {
        cartItems.add(item)
    }

    // Method to get the items in the cart
    fun getItems(): List<String> {
        return cartItems
    }

    // Method to clear the cart
    fun clearCart() {
        cartItems.clear()
    }
}