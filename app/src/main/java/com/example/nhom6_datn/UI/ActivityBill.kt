package com.example.nhom6_datn.UI

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nhom6_datn.API_Service.API_Service
import com.example.nhom6_datn.Model.BillResponse
import com.example.nhom6_datn.Model.cudan
import com.example.nhom6_datn.Retrofit.ServiceGenator
import com.example.nhom6_datn.databinding.ActivityBillBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityBill : AppCompatActivity() {

    private lateinit var binding: ActivityBillBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getSharedPreferences = getSharedPreferences("member", Context.MODE_PRIVATE)
        val modelMember = getSharedPreferences.getString("data", null)
        val gson = Gson()
        val cuDan = gson.fromJson(modelMember, cudan::class.java)
        val token = "Bearer " + cuDan.gettoken()
        showBill(token)
    }

    private fun showBill(token: String) {
        val serviceGenator = ServiceGenator().builService(API_Service::class.java)
        val call = serviceGenator.getBillByID("*/*", token, 1)
        call.enqueue(object : Callback<BillResponse?> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<BillResponse?>, response: Response<BillResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                         val billResponse=response.body()
                    with(binding){
                        billResponse?.apply {
                            tvDay.text=billDate
                            tvElectricity.text=electricityNumber.toString()
                            tvWater.text=waterNumber.toString()
                            tvOther.text="888"
                            tvTotal.text=(electricityNumber+waterNumber).toString()
                            tvRoom.text=apartment.numberOfRooms.toString()
                            tvHouseholderName.text=apartment.persons[0].name
                            tvPhone.text=apartment.persons[0].phoneNumber
                        }
                    }
                }
            }

            override fun onFailure(call: Call<BillResponse?>, t: Throwable) {

            }
        })
    }
}