package com.example.nhom6_datn

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom6_datn.API_Service.API_Service
import com.example.nhom6_datn.Model.Model_News
import com.example.nhom6_datn.Retrofit.ServiceGenator
import com.example.nhom6_datn.UI.Acti_ViewNews
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Notification_Adapter(private var context: Context, private var arrNews: MutableList<Model_News>): RecyclerView.Adapter<ViewHoderNotification>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoderNotification {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notification,parent,false)
        return ViewHoderNotification(view)
    }

    override fun onBindViewHolder(holder: ViewHoderNotification, position: Int) {
        val model = arrNews[position]
        Log.e("TAG", "onBindViewHolder: ", )
        if (model.getType()==1){
            holder._item.visibility = View.GONE
        }
        holder._item.visibility = View.VISIBLE
        holder.Bindview(model)
        holder._item.setOnClickListener {
            onclick(model)
        }

    }

    override fun getItemCount(): Int {
        if (arrNews.isNotEmpty()){

            return arrNews.size
        }
        return 0;
    }
    @SuppressLint("NotifyDataSetChanged")
    fun refeshData(arrNews: MutableList<Model_News>){
        this.arrNews = arrNews
        notifyDataSetChanged()
    }

    private fun onclick(modelNews: Model_News){
        val bundle = Bundle()
        val intent = Intent(context, Acti_ViewNews::class.java)
        bundle.putSerializable("data",modelNews)
        intent.putExtras(bundle)
        context.startActivity(intent)
    }
}

class ViewHoderNotification(view:View): RecyclerView.ViewHolder(view) {
    val _img = view.findViewById<ImageView>(R.id.itemNews_img)
    val _title = view.findViewById<TextView>(R.id.itemNews_title)
    val _message = view.findViewById<TextView>(R.id.itemNews_message)
    val _item = view.findViewById<CardView>(R.id.itemNews_news)
    val _date = view.findViewById<TextView>(R.id.itemNews_date)
    fun Bindview(modelNews: Model_News){
        val api = ServiceGenator()
        //val linkImg:List<String> = modelNews.getLink().toString().split(",");
        _title.text = modelNews.getTitle()
        _message.text = modelNews.getMessage()
        //_date.text = modelNews.getDate().toString()
//        Picasso.get().load(api._api+linkImg[0])
//            .into(_img)
    }

}