package com.example.myappeventos.event

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log

class EventAdress(var latitude:Double, var longitude:Double, var context: Context?){
    lateinit var adress:Address
    lateinit var geocoder: Geocoder
    lateinit var adressList:List<Address>

    fun findAdressByGeoLocation (): Address {
        geocoder= Geocoder(context)
        adressList = geocoder.getFromLocation(latitude,
            longitude,1)
        if(adressList.size > 0){
            adress = adressList.get(0)
        }else{
            Log.d("Adress is null", "true")
        }
        return adress
    }
}