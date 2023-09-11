package com.example.nhom6_datn.Model

import java.util.Objects

class cudan :java.io.Serializable{
    private  var token:String
    private  var isAuthenticated:Boolean

    constructor(token: String, isAuthenticated: Boolean) {
        this.token = token
        this.isAuthenticated = isAuthenticated
    }
    fun gettoken():String{
        return token
    }
}