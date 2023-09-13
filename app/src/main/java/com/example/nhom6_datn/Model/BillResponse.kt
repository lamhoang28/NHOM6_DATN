package com.example.nhom6_datn.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class BillResponse(
    val apartment: Apartment,
    val billDate: String,
    val electricityNumber: Int,
    val feeTypeList: List<Int>,
    val id: Int,
    val paidDate: String,
    val status: Boolean,
    val waterNumber: Int
):Parcelable