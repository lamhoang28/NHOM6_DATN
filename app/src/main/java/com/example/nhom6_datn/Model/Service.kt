package com.example.nhom6_datn.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Service(
    val id: Int,
    val message: String,
    val status: Int,
    val type: Int,
) : Parcelable