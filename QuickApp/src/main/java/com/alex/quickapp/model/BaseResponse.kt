package com.alex.quickapp.model

class BaseResponse<T>(val result: T, val code: Int?, val message: String?) {

}