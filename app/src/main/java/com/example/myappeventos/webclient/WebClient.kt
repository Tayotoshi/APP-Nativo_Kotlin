package com.example.myappeventos.webclient

import android.content.Context
import android.os.Looper
import android.util.Log
import com.example.myappeventos.call.screen.CallScreen
import com.example.myappeventos.interfaces.ApiRequest
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class WebClient: ApiRequest{
    override val urlBase: String = "http://5b840ba5db24a100142dcd8c.mockapi.io/api"
    override val client: OkHttpClient= OkHttpClient()
    override var postOn:Boolean = false
    override var getOn:Boolean = false
    override var context:Context? = null
    /**
     *  Companion object, that'll say what kind of Json media type,
     *  we will use.
     */
    companion object {
        val JSON = "application/json; charset=utf-8".toMediaType()
    }

    /**
     * Get fun, it's responsible to create a HTTP get request to
     * urlBase and his route, then it returns an another fun, called
     * requestResult().
     */
    override fun get(url: String, contextScreen: Context): JSONArray?{
        context = contextScreen
        getOn = true
        Log.d("Ativando", "tela de loading")
        Log.d("Func get called on", urlBase+url)
        val request = Request.Builder()
            .url(urlBase+url)
            .build()
        Log.d("RequestGET", request.toString())
        return requestResult(request)
    }
    /**
     * Post fun, it's responsible to create a HTTP post request to
     * urlBase and his route, then it returns an another fun, called
     * requestResult().
     */
    override fun post(url: String, data: JSONObject): JSONArray?{
        postOn = true
        Log.d("Loading screen", "ON!")
        Log.d("Func post called on", urlBase + url)
        Log.d("Sending data", data.toString())
        val body = RequestBody.create(JSON, data.toString())
        val request = Request.Builder()
            .method("POST", body)
            .url(urlBase + url)
            .build()
        Log.d("RequestPOST", request.toString())
        return requestResult(request)
    }
    /**
     * Fun requestResult(), its responsible to receive an request from POST or GET,
     * and then it'll make an requisition using OkHttp3, tha will give us an response (CallBack),
     * that will tell if the requisition done was successful or not.
     */
    /**
     * If the get fun was called, it will then call the EventsActivity.
     */
    override fun requestResult(request: Request): JSONArray? {
        var responseData = ""
        var responseJson:JSONArray?= null
        try {
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    responseData =  "Something went wrong"
                    Log.d("UnSuccess return", responseData)
                    Log.d("Loading screen", "OFF!")
                }
                override fun onResponse(call: Call, response: Response) {
                    Looper.prepare()
                    Log.d("Success return", responseData)
                    responseData = response.body!!.string()
                    if (getOn){
                        responseJson = JSONArray(responseData)
                        Log.d("GET return", responseJson.toString())
                        val callActivity = CallScreen()
                        callActivity.callEventsActivity(context!!, responseJson)
                        getOn = false
                    }else if(postOn){
                        Log.d("POST return", responseData)
                        postOn = false
                    }
                    Log.d("Loading screen", "OFF!")
                }
            })
            return responseJson
        } catch (e: Exception) {
            Log.e("NETWORK", "err general: " + e.message)
            Log.e("NETWORK", "err print: " + e.localizedMessage)
            e.printStackTrace()
            return responseJson
        }
    }
}