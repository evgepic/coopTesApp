package com.example.cooptesapp.database

class PriceConverter {

    companion object {

        fun convertPrice(price: Long, dimension: String): String {
            return (price / 100).toString() + "." + (price % 100) + " руб." + " за ${dimension}"
        }

        fun convertPrice(price: Long): String {
            return (price / 100).toString() + "." + (price % 100) + " руб."
        }
    }

}

