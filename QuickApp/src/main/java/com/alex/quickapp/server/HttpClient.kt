package com.alex.quickapp.server

import com.alex.quickapp.BuildConfig
import com.alex.quickapp.model.BaseResponse
import com.alex.quickapp.model.Ticket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClient {
    private const val mBaseUrl = ""

    private val mHttpClient =
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
            if (BuildConfig.DEBUG) {
                it.level = HttpLoggingInterceptor.Level.BODY
            } else {
                it.level = HttpLoggingInterceptor.Level.BASIC
            }
        }).build()

    private val mRetrofit = Retrofit.Builder()
        .baseUrl(mBaseUrl).addConverterFactory(GsonConverterFactory.create()).client(mHttpClient)
        .build()

    private val mService = mRetrofit.create(ApiService::class.java)

    private suspend fun <T : Any> apiCall(call: suspend () -> BaseResponse<T>): BaseResponse<T> {
        return withContext(Dispatchers.IO) { call.invoke() }.apply {
            // 特殊处理
            when (code) {
                99999 -> {
                }
                80000 -> {
                }
            }
        }
    }

    suspend fun verifyKey(key: String): BaseResponse<Ticket> {
        return apiCall {
            mService.verifyKey(key)
        }
    }
}