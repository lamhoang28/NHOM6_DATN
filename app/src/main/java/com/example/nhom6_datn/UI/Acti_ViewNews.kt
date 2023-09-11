package com.example.nhom6_datn.UI

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel
import com.example.nhom6_datn.API_Service.API_Service
import com.example.nhom6_datn.Model.Model_Comment
import com.example.nhom6_datn.Model.Model__teset
import com.example.nhom6_datn.Model.Model_News
import com.example.nhom6_datn.R
import com.example.nhom6_datn.Retrofit.ServiceGenator
import com.example.nhom6_datn.databinding.ActivityActiViewNewsBinding
import com.example.nhom6_datn.util.shower
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import kotlin.collections.ArrayList

class Acti_ViewNews : AppCompatActivity() {

    private var list = kotlin.collections.ArrayList<Model_Comment>()
    private lateinit var recyclerView : RecyclerView
    private lateinit var manager : LinearLayoutManager
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout


    private lateinit var binding:ActivityActiViewNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acti_view_news)
        binding=ActivityActiViewNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageSlide = binding.ActiViewNewsImageSlider
        val intent = intent
        val bundle = intent.extras ?: return
        val model:Model_News = bundle.getSerializable("data") as Model_News
        val title = binding.ActiViewNewsTitle
        val message = binding.ActiViewNewsMessage
        val date = binding.ActiViewNewsDate
        title.text = model.getTitle()
        message.text = model.getMessage()
        //date.text = model.getDate().toString()

        val arr = ArrayList<SlideModel>()
        val api = ServiceGenator()
        //val arrImg = model.getLink().split(",")

//        for (i in arrImg){
//            val linkImg =api._api+i;
//            arr.add(SlideModel(linkImg,ScaleTypes.CENTER_CROP))
//        }
//        imageSlide.setImageList(arr)

    }

}