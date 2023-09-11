package com.example.nhom6_datn.Model

import java.util.Objects

class cuDan_apartment :java.io.Serializable{
    private  var id:Int
    private  var name:String
    private  var email:String
    private  var phoneNumber:String
    private  var cardIdNumber:String
    private  var birthDate:String
    private  var gender:Boolean
    private  var representative:Boolean
    private lateinit var apartment: Any

    constructor(
        id: Int,
        name: String,
        email: String,
        phoneNumber: String,
        cardIdNumber: String,
        birthDate: String,
        gender: Boolean,
        representative: Boolean,
    ) {
        this.id = id
        this.name = name
        this.email = email
        this.phoneNumber = phoneNumber
        this.cardIdNumber = cardIdNumber
        this.birthDate = birthDate
        this.gender = gender
        this.representative = representative
    }


    fun getId():Int{
        return id
    }

    fun getNameCdan():String{
        return name
    }

    fun getEmail():String{
        return email
    }
    fun getAge():String{
        return birthDate
    }
    fun getApartment():Any{
        return apartment
    }
    fun getPhone():String{
        return phoneNumber
    }
    fun getCardIdNumber():String{
        return cardIdNumber
    }

}