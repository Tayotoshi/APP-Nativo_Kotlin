package com.example.myappeventos.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myappeventos.R
import kotlinx.android.synthetic.main.fragment_events.view.*
import org.json.JSONObject


class  EventDescFragment(var jsonEventsData: JSONObject?): Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        Log.d("EventsFragment", "Created!")
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mView: View = layoutInflater.inflate(R.layout.custom_dialog, null, false)
        /**
         * Views id's values.
         */
        val eventImage = view.event_image_display
        val eventTitle = view.event_title_display
        val eventDate = view.event_date_display
        val eventDesc = view.event_desc_display
        val eventAdress = view.event_place_display
        val eventCheckin = view.floating_button
        val eventValue = view.event_value_display
        /**
         * Latitude and longitude values.
         */
        val latitude= jsonEventsData!!.getDouble("latitude")
        val longitude= jsonEventsData!!.getDouble("longitude")
        val eventId = jsonEventsData!!.getString("id")
        /**
         * Adress values.
         */
        val adressFinder = EventAdress(latitude,longitude,context)
        val adressReturn = adressFinder.findAdressByGeoLocation()
        val adressFinal = adressReturn.getAddressLine(0)
        /**
         * Variable that instances the callDialog class,
         * and then it calls the method callDialogCheckin.
         */
        val callDialog = CallDialog()
        callDialog.callDialogCheckin(eventCheckin,context,mView,eventId)
        /**
         * Download the URL's images,
         * and the put it in those respective views.
         */
        val imagemLoader = ImagemLoader()
        imagemLoader.loadImageFromUrl(jsonEventsData!!.getString("image"),eventImage,context)
        /**
         * Set each textView with your respective text.
         */
        val price = "R$ "+jsonEventsData!!.getString("price")
        eventAdress.setText(adressFinal)
        eventValue.setText(price)
        eventTitle.setText(jsonEventsData!!.getString("title"))
        eventDesc.setText(jsonEventsData!!.getString("description"))
        eventDate.setText("25/10/1999")
    }
}