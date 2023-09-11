package com.example.nhom6_datn.Model

class Apartment {
    private var id:Int
    private var apartmentNumber:String
    private var area:Int
    private var numberOfRooms:Int
    private var status:Boolean

    constructor(id: Int, apartmentNumber: String, area: Int, numberOfRooms: Int, status: Boolean) {
        this.id = id
        this.apartmentNumber = apartmentNumber
        this.area = area
        this.numberOfRooms = numberOfRooms
        this.status = status
    }


    fun getNumberOfRooms():Int{
        return numberOfRooms
    }
}