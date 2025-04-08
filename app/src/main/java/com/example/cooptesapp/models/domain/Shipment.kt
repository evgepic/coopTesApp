package com.example.cooptesapp.models.domain

import com.example.cooptesapp.database.ShipmentEntity

data class Shipment(
    val name: String,
    val barcode: Barcode,
    val dimension: Dimension,
    val type: Long,
    val price: Price,
    val quantity: Long
)

data class Barcode(
    val body: String
)

data class Dimension(
    val name: String
)

data class Price(
    val amount: Long,
    val bonus: Long
)

fun ShipmentEntity.toShipment(): Shipment =
    Shipment(
        this.packEntity.packName,
        Barcode(this.barcodeEntity.body),
        Dimension(this.unitEntity.unitName),
        this.packEntity.type,
        Price(this.priceEntity.price, this.priceEntity.bonus),
        this.packEntity.quant
    )