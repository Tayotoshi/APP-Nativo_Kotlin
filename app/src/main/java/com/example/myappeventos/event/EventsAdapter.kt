package com.example.myappeventos.event

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myappeventos.R
import kotlinx.android.synthetic.main.activity_events_list.view.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class EventsAdapter(eventsToDisplay:JSONArray?, var mListener: EventsFragment.OnListFragmentInteractionListener?,var context:Context):RecyclerView.Adapter<EventsAdapter.EventsHolder>() {
    var items: JSONArray? = eventsToDisplay
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_events_list, parent, false)
        return EventsHolder(view)
    }

    override fun onBindViewHolder(holder: EventsHolder, position: Int) {
        val data: JSONObject = items?.get(position) as JSONObject
        val url = data.getString("image")
        val imagemLoader = ImagemLoader()
        val latitude= data.getDouble("latitude")
        val longitude= data.getDouble("longitude")
        Log.d("Latitude", latitude.toString())
        Log.d("Longitude", longitude.toString())
        val adressFinder = EventAdress(latitude,longitude,context)
        val adressReturn = adressFinder.findAdressByGeoLocation()
        Log.d("AdressReturn", adressReturn.toString())
        val streetName = adressReturn.thoroughfare
        val postalCode = adressReturn.featureName
        var finalAdress = streetName+","+postalCode

        holder.eventTitle.setText(data.getString("title"))
        holder.eventAdress.setText(finalAdress)
        holder.checkinButton.setOnClickListener {
            holder.mView.callOnClick()
        }
        imagemLoader.loadImageFromUrl(url,holder.eventImage,context)
        holder.mView.setOnClickListener {
            if (null != mListener) {
                try {
                    mListener!!.onListFragmentInteraction(data)
                    Log.d("Item", "$position selected!")
                }catch (e: JSONException) {
                    e.printStackTrace()
                }
            }else{
                Log.d("mListerner", "is null!")
            }
        }
    }

    override fun getItemCount(): Int {
        return items!!.length()
    }

    class EventsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mView = itemView
        var eventImage = itemView.card_background
        var eventTitle = itemView.card_event_name
        var eventAdress = itemView.card_local_event
        var checkinButton = itemView.card_checkin_btn
    }
}