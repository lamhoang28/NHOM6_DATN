package com.example.nhom6_datn.UI

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.nhom6_datn.API_Service.API_Service
import com.example.nhom6_datn.Model.Apartment
import com.example.nhom6_datn.Model.Model__teset
import com.example.nhom6_datn.Model.cuDan_apartment
import com.example.nhom6_datn.R
import com.example.nhom6_datn.Retrofit.ServiceGenator
import com.example.nhom6_datn.databinding.ActivityActiViewInfoBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class Acti_ViewInfo : AppCompatActivity() {
    private lateinit var name:TextView
    private lateinit var age:TextView
    private lateinit var address:TextView
    private lateinit var phone:TextView
    private lateinit var cccd:TextView
    private lateinit var binding:ActivityActiViewInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acti_view_info)
        binding = ActivityActiViewInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getSharedPreferences  = getSharedPreferences("member",Context.MODE_PRIVATE)
        val cuDan = getSharedPreferences.getString("cuDan",null)
        val gson = Gson()
        var info = gson.fromJson(cuDan, cuDan_apartment::class.java)
        binding.name.text = info.getNameCdan()
        binding.age.text = info.getAge()
        binding.phone.text = info.getPhone()
        binding.cccd.text =info.getCardIdNumber()

        binding.room.text = getSharedPreferences.getString("room",null)
    }
}