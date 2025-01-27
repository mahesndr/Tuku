package com.example.tuku

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    // Daftar item dalam keranjang
    val cartItems = mutableStateListOf<Item>()

    // Fungsi untuk menambahkan item ke keranjang
    fun addItemToCart(item: Item) {
        cartItems.add(item)
    }

    // Fungsi untuk menghapus item dari keranjang (opsional)
    fun removeItemFromCart(item: Item) {
        cartItems.remove(item)
    }

    // Hitung total harga (opsional)
    fun calculateTotal(): Double {
        return cartItems.sumOf { it.id * 10.0 } // Contoh harga berdasarkan id * 10
    }
}
