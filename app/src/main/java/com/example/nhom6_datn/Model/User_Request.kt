package com.example.nhom6_datn.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User_Request {
    @SerializedName("username")
    @Expose
    lateinit var userN:String

    @SerializedName("password")
    @Expose
    lateinit var passW:String

}