package com.example.nhom6_datn.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val birthDate: String,
    val cardIdNumber: String,
    val email: String,
    val gender: Boolean,
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val representative: Boolean
):Parcelable