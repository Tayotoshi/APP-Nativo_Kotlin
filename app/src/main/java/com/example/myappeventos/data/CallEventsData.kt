package com.example.myappeventos.data

import android.content.Context
import com.example.myappeventos.webclient.WebClient
import org.json.JSONObject

class CallEventsData {
    val webClient = WebClient()

    /**
     * Call the get method, passing /events
     * as route.
     */
    fun getData(context: Context) {
        webClient.get("/events",context)
    }
    /**
     * Call the post method, passing /checkin
     * as route.
     */
    fun postData(ticket: JSONObject) {
        webClient.post("/checkin", ticket)
    }
}


