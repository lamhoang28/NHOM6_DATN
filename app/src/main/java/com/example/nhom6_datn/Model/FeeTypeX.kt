package com.example.nhom6_datn.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeeTypeX(
    val id: Int,
    val name: String,
    val price: Int
):Parcelable