package com.example.myappeventos.data

import android.util.Log
import org.json.JSONObject


class CallPost {
    /**
     * It'll create a JSONObject tha will be passed as parameter
     * to the fun postData from CallEventsData class.
     */
    fun createCheckin(
        eventId: String,
        mEmail: String,
        mName: String
    ) {
        val callPost = CallEventsData()
        val ticket = JSONObject()
        ticket.put("eventId", eventId)
        ticket.put("name",mName)
        ticket.put("email",mEmail)
        Log.d("TicketCheckout", ticket.toString())
        callPost.postData(ticket)
    }
}