package com.example.nhom6_datn.UI

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom6_datn.API_Service.API_Service
import com.example.nhom6_datn.Model.Model_News
import com.example.nhom6_datn.Model.cuDan_apartment
import com.example.nhom6_datn.Model.cudan
import com.example.nhom6_datn.News_Adapter
import com.example.nhom6_datn.R
import com.example.nhom6_datn.Retrofit.ServiceGenator
import com.example.nhom6_datn.UI.Manager.Acti_Manager
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Frag_Home : Fragment() {
    private lateinit var service:Button
    private lateinit var news:Button
    private lateinit var btnBill:Button

    private lateinit var linearLayoutManager:LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: News_Adapter
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private var list= ArrayList<Model_News>()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var TAG:String="Frag_Home"



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_frag__home, container, false)

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.Frag_Home_helloProfile)
        val imgSky = view.findViewById<ImageView>(R.id.Frag_Home_imgSky)
        val nameMember = view.findViewById<TextView>(R.id.Frag_Home_nameProFile)
        val roomMember = view.findViewById<TextView>(R.id.Frag_Home_roomProFile)


        val sharedPreferences: SharedPreferences = this.requireActivity().getSharedPreferences("member",
            Context.MODE_PRIVATE)
        val modelMember:String? = sharedPreferences.getString("data",null)
        val gson = Gson()
        var cuDan = gson.fromJson(modelMember, cudan::class.java)

        Log.e("TAG", "onResponse: "+cuDan )


        val token = "Bearer "+cuDan.gettoken()
        readInfoToken(nameMember,roomMember,token)
        checkDate(textView,imgSky)
        call(view)

        news.setOnClickListener {
           //Toast(context).shower("Update "+getString(R.string.successful),10, context as Activity)
            startActivity(Intent(context,Acti_News::class.java))
        }
        service.setOnClickListener {
            startActivity(Intent(context,Acti_Manager::class.java))
        }
        btnBill.setOnClickListener {
            startActivity(Intent(context,ActivityBill::class.java))
        }

//        statistical.setOnClickListener {
//            startActivity(Intent(context,Acti_Statistical::class.java))
//        }
        callReposn(token)
    }
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun checkDate(textView: TextView,imageView: ImageView){
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val time = SimpleDateFormat("HH")
        val currentDate = time.format(Date())
        when(currentDate.toInt()){
            in 0..10 ->textView.text=getString(R.string.hello_Profile_AM)
            in 11..18 -> {
                textView.text=getString(R.string.hello_Profile_CM)
                imageView.setImageResource(R.drawable.sky_cm)
            }
            else -> {
                textView.text=getString(R.string.hello_Profile_PM)
                imageView.setImageResource(R.drawable.sky_pm)
            }
        }
    }
    private fun call(view: View){
        service = view.findViewById(R.id.Frag_home_AppCompatButton_service)
        news = view.findViewById(R.id.Frag_home_AppCompatButton_news)
        btnBill = view.findViewById(R.id.Frag_home_AppCompatButton_bill)
        linearLayoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        recyclerView= view.findViewById(R.id.RecyclerView)
        recyclerView.layoutManager = linearLayoutManager
        shimmerFrameLayout = view.findViewById(R.id.Frag_home_ShimmerFrameLayout)
    }

    private fun callReposn(token:String){
        val serviceGenator = ServiceGenator().builService(API_Service::class.java)
        val call = serviceGenator.getPost("*/*",token)
        call.enqueue(object :retrofit2.Callback<MutableList<Model_News>>{
            override fun onResponse(
                call: Call<MutableList<Model_News>>,
                response: Response<MutableList<Model_News>>
            ) {
                if (response.isSuccessful&&response.body()!!.size>0){
                    shimmerFrameLayout.stopShimmer()
                    shimmerFrameLayout.visibility = View.GONE
                    val data = response.body()!!
                    list.add(data[0])
                    adapter = News_Adapter(context as Activity,list)
                    recyclerView.hasFixedSize()
                    recyclerView.adapter = adapter

                }else{
                    shimmerFrameLayout.stopShimmer()
                    shimmerFrameLayout.visibility = View.GONE
                }

            }

            override fun onFailure(call: Call<MutableList<Model_News>>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "onResponse: "+t.message.toString() )
            }

        })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    @SuppressLint("CommitPrefEdits")
    private fun readInfoToken(nameMember:TextView,roomMember:TextView,passW:String){
                val serviceGenator = ServiceGenator().builService(API_Service::class.java)
                val call = serviceGenator.checkMember("*/*",passW)
                call.enqueue(object : retrofit2.Callback<cuDan_apartment>{
                    override fun onResponse(call: Call<cuDan_apartment>, response: Response<cuDan_apartment>) {
                        if (response.isSuccessful){

                            val gson = Gson()
                            var apartment = response.body()?.getApartment()
                            Log.e(TAG, "onResponse: "+apartment)



                            nameMember.text = response.body()?.getNameCdan()
                }
            }

            override fun onFailure(call: Call<cuDan_apartment>, t: Throwable) {
                Log.e(TAG, "loi roi"+t.message )
            }

        })
    }


}