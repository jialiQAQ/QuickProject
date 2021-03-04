package com.alex.quickapp.server

import com.alex.quickapp.model.BaseResponse
import com.alex.quickapp.model.Ticket
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {
    @POST("/verify")
    suspend fun verifyKey(@Field("key") key: String): BaseResponse<Ticket>
}