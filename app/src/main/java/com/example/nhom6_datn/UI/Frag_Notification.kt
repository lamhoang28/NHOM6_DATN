package com.example.nhom6_datn.UI

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.nhom6_datn.API_Service.API_Service
import com.example.nhom6_datn.Model.Model_News
import com.example.nhom6_datn.Model.cudan
import com.example.nhom6_datn.News_Adapter
import com.example.nhom6_datn.Notification_Adapter
import com.example.nhom6_datn.R
import com.example.nhom6_datn.Retrofit.ServiceGenator
import com.example.nhom6_datn.databinding.ActivityActiNewsBinding
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response


class Frag_Notification : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: LinearLayoutManager
    private lateinit var adapter: Notification_Adapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private var list= ArrayList<Model_News>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_frag__notification, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val getSharedPreferences  = requireActivity().getSharedPreferences("member", Context.MODE_PRIVATE)
        val modelMember = getSharedPreferences.getString("data",null)
        val gson = Gson()
        var cuDan = gson.fromJson(modelMember, cudan::class.java)
        val token = "Bearer "+cuDan.gettoken()

        recyclerView = view.findViewById(R.id.RecyclerView_notification)
        manager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        swipeRefreshLayout = view.findViewById(R.id.Notification_SwipeRefreshLayout)

        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)

        shimmerFrameLayout = view.findViewById(R.id.Notification_ShimmerFrameLayout)
        swipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                CallAPI(token)
                swipeRefreshLayout.isRefreshing= false
            },3000)
        }
        list = ArrayList()

        CallAPI(token)
    }
    private fun CallAPI(token:String){
        val serviceGenator = ServiceGenator().builService(API_Service::class.java)
        val call = serviceGenator.getPost("*/*",token)
        call.enqueue(object :retrofit2.Callback<MutableList<Model_News>>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<MutableList<Model_News>>,
                response: Response<MutableList<Model_News>>
            ) {
                if(response.isSuccessful&&response.body()!!.size>0){
                    shimmerFrameLayout.stopShimmer()
                    shimmerFrameLayout.visibility = View.GONE
                    val data = response.body()!!
                    list.clear()
                    for (i in data){
                        if (i.getType()==1){
                            Log.e("TAG", "onResponse: "+i.getTitle() )
                            list.add(i)
                        }
                    }

                    adapter = Notification_Adapter(requireActivity(),list)
                    recyclerView.adapter = adapter
                    recyclerView.hasFixedSize()
                    adapter.notifyDataSetChanged()

                }else{
                    shimmerFrameLayout.stopShimmer()
                    shimmerFrameLayout.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<MutableList<Model_News>>, t: Throwable) {
                t.printStackTrace()
                Log.e("TAG", "onResponse: "+t.message.toString() )
            }

        })
    }
}