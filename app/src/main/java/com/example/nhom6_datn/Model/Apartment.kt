package com.example.nhom6_datn.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Apartment(
    val apartmentNumber: String,
    val area: Int,
    val bills: List<Bill>,
    val id: Int,
    val numberOfRooms: Int,
    val persons: List<Person>,
    val services: List<Service>,
    val status: Boolean
):Parcelable