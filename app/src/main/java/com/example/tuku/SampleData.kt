package com.example.tuku

import com.example.tuku.R

data class Item(
    val id: Int,
    val title: String,
    val description: String,
    val image: Int,
    var price: Double,
    var quantity: Int = 1
)

// Data contoh
val sampleItems = listOf(
    Item(
        id = 1,
        title = "Burger Meal",
        description = "Satu paket lengkap berisi burger dengan double patty daging sapi, keju leleh, sayuran segar, roti wijen, kentang goreng renyah, dan segelas minuman soda. Cocok untuk Anda yang menginginkan kepuasan maksimal dalam setiap gigitan.",
        image = R.drawable.sample_image_1,
        price = 10.0,
        quantity = 1
    ),
    Item(
        id = 2,
        title = "Single Burger",
        description = "Burger sederhana namun lezat, terdiri dari patty daging sapi yang juicy, keju leleh, saus tomat, mustard, dan roti lembut. Pilihan sempurna untuk makan cepat namun memuaskan.",
        image = R.drawable.sample_image_2,
        price = 20.0,
        quantity = 1
    ),
    Item(
        id = 3,
        title = "Crispy Fried Chicken",
        description = "Potongan ayam goreng dengan balutan tepung berbumbu yang renyah di luar dan juicy di dalam. Disajikan dalam porsi yang pas untuk Anda nikmati kapan saja.",
        image = R.drawable.sample_image_3,
        price = 15.0,
        quantity = 1
    ),
    Item(
        id = 4,
        title = "Chicken Nuggets",
        description = "Potongan kecil ayam berlapis tepung renyah, disajikan dengan kentang goreng. Cocok untuk camilan atau makanan ringan yang nikmat.",
        image = R.drawable.sample_image_4,
        price = 25.0,
        quantity = 1
    ),
    Item(
        id = 5,
        title = "Grilled Chicken Wrap",
        description = "Wrap tortilla isi ayam panggang, sayuran segar, dan saus creamy spesial. Pilihan yang praktis dan lezat untuk dinikmati saat bepergian.",
        image = R.drawable.sample_image_5,
        price = 30.0,
        quantity = 1
    )
)
