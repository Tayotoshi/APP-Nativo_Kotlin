package com.example.myappeventos.interfaces

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

interface ApiRequest{
    val urlBase:String
    val client:OkHttpClient
    var postOn:Boolean
    var getOn:Boolean
    var context: Context?

    fun get (url: String,contextScreen:Context): JSONArray?
    fun post (url: String, data: JSONObject): JSONArray?
    fun requestResult (request: Request): JSONArray?
}