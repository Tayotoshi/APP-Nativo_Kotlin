package com.example.myappeventos.event

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myappeventos.R
import com.example.myappeventos.event.EventsFragment.OnListFragmentInteractionListener
import org.json.JSONArray
import org.json.JSONObject

class EventsActivity: AppCompatActivity(), OnListFragmentInteractionListener{
    companion object{
        var eventsArray:JSONArray? = null
        lateinit var fm:FragmentManager

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fm = supportFragmentManager
        setContentView(R.layout.activity_events)
        Log.d("EventsActivity", "created !")
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_holder, EventsFragment.newInstance(eventsArray), "eventList")
                .commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListFragmentInteraction(item: JSONObject?) {
        fun getFragmentTransaction(): FragmentTransaction? {
            val ft: FragmentTransaction = fm.beginTransaction()
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ft.addToBackStack(null)
            return ft
        }
        val ft: FragmentTransaction? = getFragmentTransaction()

        if (ft != null) {
            ft.addToBackStack(null)
            ft.replace(R.id.fragment_holder, EventDescFragment(item))
            ft.commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}