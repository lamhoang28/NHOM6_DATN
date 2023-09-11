package com.example.nhom6_datn.API_Service

import com.example.nhom6_datn.Model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface API_Service {

    @POST("/login")
    fun login(
        @Body request: User_Request
    ):Call<cudan>

    @GET("/api/person/1")
    fun checkMember(
        @Header("accept") accept:String,@Header("Authorization") passW:String
    ):Call<cuDan_apartment>


    @GET("/checkMember")
    fun checkToken(
        @Query("tokenn") tokenn:String,@Query("passW") passW:String
    ):Call<Model__teset>


    @GET("api/news")
    fun getPost(
        @Header("accept") accept:String,@Header("Authorization") passW:String
    ):Call<MutableList<Model_News>>

    @GET ("/newsCommentApi/{id}")
    fun getApiComment(
        @Path("id") _id:Int
    ):Call<List<Model_Comment>>


    @PUT("/users/changePassWord/{id}")
    fun changePassWord(
        @Path("id") _id:String ,@Body change:User_Change_Pass
    ):Call<User_Change_Pass>
}