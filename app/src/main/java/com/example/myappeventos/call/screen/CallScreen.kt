package com.example.myappeventos.call.screen

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.example.myappeventos.event.EventsActivity
import com.example.myappeventos.event.EventsActivity.Companion.eventsArray
import org.json.JSONArray


class CallScreen {
    /**
     * Call the EventsActivity with an JSONArray?,
     * as parameter.
     */
    fun callEventsActivity (
        context: Context,
        responseJson: JSONArray?
    ){
        Log.d("Calling Activity", "EventsActivity")
        val eventsActivity = EventsActivity()
        eventsArray = responseJson

        val intent = Intent(context, eventsActivity::class.java)
        startActivity(context, intent, null)
    }
}