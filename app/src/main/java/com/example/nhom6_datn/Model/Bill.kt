package com.example.nhom6_datn.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bill(
    val billDate: String,
    val electricityNumber: Int,
    val feeTypes: List<Int>,
    val id: Int,
    val paidDate: String,
    val status: Boolean,
    val waterNumber: Int
):Parcelable