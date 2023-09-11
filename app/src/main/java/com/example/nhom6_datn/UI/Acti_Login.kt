package com.example.nhom6_datn.UI

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import com.example.nhom6_datn.API_Service.API_Service
import com.example.nhom6_datn.MainActivity
import com.example.nhom6_datn.Model.User_Request
import com.example.nhom6_datn.Model.cudan
import com.example.nhom6_datn.R;
import com.example.nhom6_datn.Retrofit.ServiceGenator
import com.example.nhom6_datn.databinding.ActivityActiLoginBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class Acti_Login : AppCompatActivity() {
    private lateinit var binding: ActivityActiLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acti_login)
        binding = ActivityActiLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ani =AnimationUtils.loadAnimation(this,R.anim.fade_zoom).apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
        }

        checkGmail()
        checkPass()
        loadData()
        binding.ActiLoginAppCompatButton.setOnClickListener {
            var _gmail = binding.ActiLoginEmail.editText?.text.toString().trim()
            var _pass = binding.ActiLoginPassWord.editText?.text.toString().trim()
            var check=0

            if (_gmail.isEmpty()){
                binding.ActiLoginEmail.error = getString(R.string.ERROR)
            }else{
                check++
            }
            if (_pass.isEmpty()){
                binding.ActiLoginPassWord.error = getString(R.string.ERROR)
            }else{
                check++
            }
            if (check==2){
                checkLogin(_gmail,_pass)

//                Toast(this).shower("Update "+getString(R.string.successful),1, this as Activity)
//                binding.ActiLoginView.isInvisible = true
//                binding.ActiLoginView.startAnimation(ani){
//                    startActivity(Intent(this,MainActivity::class.java))
//                    finish()
////                Handler(Looper.getMainLooper()).postDelayed({
////
////                },100)
//                }
                check=0
            }


        }
    }

    private fun checkPass() {
        binding.ActiLoginPassWord.editText?.setOnFocusChangeListener { view, text ->
            if (!text){
                binding.ActiLoginPassWord.error = validate(binding.ActiLoginPassWord.editText!!.text.toString().trim())
            }
        }
    }

    private fun checkGmail(){
        binding.ActiLoginEmail.editText?.setOnFocusChangeListener { view, text ->
            if (!text){
                binding.ActiLoginEmail.error = validate(binding.ActiLoginEmail.editText!!.text.toString().trim())
            }
        }
    }

    private fun validate(text:String): String? {
        if(text.isEmpty()){
            return getString(R.string.ERROR)
        }
        return null
    }
    private fun checkLogin(userN:String,passW: String){
        val serviceGenator = ServiceGenator().builService(API_Service::class.java)
        val request = User_Request()
        request.userN = userN
        request.passW = passW
        val call =serviceGenator.login(request)
        call.enqueue(object : retrofit2.Callback<cudan>{
            override fun onResponse(call: Call<cudan>, response: Response<cudan>) {
                if (response.isSuccessful&&response.body().toString().isNotEmpty()){
                    val model = response.body() as cudan
                    Log.e("TAG" ,"onResponse: "+response.body())
                    //Log.e("TAG", "onResponse: "+model.gettoken() )
                    saveData(model,userN,passW)
                    startActivity(Intent(this@Acti_Login,MainActivity::class.java))
                    finish()
                }
            }

            override fun onFailure(call: Call<cudan>, t: Throwable) {
                Log.e("TAG", "onFailure: "+t.message )
            }

        })
    }
    @SuppressLint("CommitPrefEdits")
    private fun saveData(model:cudan,userN: String,passW: String){
        val sharedPreferences = getSharedPreferences("member",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if(!binding.ActiLoginSwitch.isChecked){
            editor.clear()
        }else{
            editor.putString("userN",userN)
            editor.putString("passW",passW)
            editor.putBoolean("key",binding.ActiLoginSwitch.isChecked)
        }
        val gson= Gson()
        val arr = gson.toJson(model)
        editor.putString("data",arr)
        editor.putString("passW",passW)
        editor.apply()
    }
    private fun loadData(){
        val sharedPreferences = getSharedPreferences("member",Context.MODE_PRIVATE)
        val saveUser:String? = sharedPreferences.getString("userN",null)
        val savePssW:String? = sharedPreferences.getString("passW",null)
        val saveKey:Boolean = sharedPreferences.getBoolean("key",false)
        binding.ActiLoginEmail.editText?.setText(saveUser)
        binding.ActiLoginPassWord.editText?.setText(savePssW)
        binding.ActiLoginSwitch.isChecked= saveKey
    }
}