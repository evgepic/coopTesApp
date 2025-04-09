package com.example.cooptesapp.models.domain

import android.os.Parcel
import android.os.Parcelable

data class DraftModel(
    val packId: Long = 0,
    val name: String = "",
    val barcode: String = "",
    val price: Long = 0,
    val discount: Long = 0,
    var amount: Long = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(packId)
        parcel.writeString(name)
        parcel.writeString(barcode)
        parcel.writeLong(price)
        parcel.writeLong(discount)
        parcel.writeLong(amount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DraftModel> {
        override fun createFromParcel(parcel: Parcel): DraftModel {
            return DraftModel(parcel)
        }

        override fun newArray(size: Int): Array<DraftModel?> {
            return arrayOfNulls(size)
        }
    }
}