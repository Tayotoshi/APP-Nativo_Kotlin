package com.example.myappeventos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myappeventos.data.CallEventsData

class MainActivity : AppCompatActivity(){
    val getEvents = CallEventsData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreateMain", "True")
        setContentView(R.layout.main_layout)
        getEvents.getData(this)
    }
}
