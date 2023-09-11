package com.example.nhom6_datn.Model

import java.util.Date

class Model_News :java.io.Serializable{
    private  var id:Int
    private  var title:String
    private  var message:String
    private  var type:Int

    constructor(id: Int, title: String, message: String, type: Int) {
        this.id = id
        this.title = title
        this.message = message
        this.type = type
    }


    fun getId_news():Int{
        return id
    }
    fun getTitle():String{
        return title
    }
    fun getMessage():String{
        return message
    }


}