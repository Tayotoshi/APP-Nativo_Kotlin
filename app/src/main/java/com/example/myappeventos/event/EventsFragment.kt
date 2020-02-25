package com.example.myappeventos.event

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myappeventos.R
import kotlinx.android.synthetic.main.activity_events.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class  EventsFragment(arrayJSON:JSONArray? = null): Fragment(){
    lateinit var eventsAdapter:EventsAdapter
    private var arrayJSONteste= arrayJSON
    var mListener: OnListFragmentInteractionListener? = null
    companion object {
        fun newInstance(arrayJson:JSONArray?): EventsFragment {
            return EventsFragment(arrayJson)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        Log.d("EventsFragment", "Created!")
        return inflater.inflate(R.layout.activity_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startRecyclerView(arrayJSONteste)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is OnListFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnListFragmentInteractionListener"
            )
        }
    }
    fun startRecyclerView(eventsArray: JSONArray?){
        if (eventsArray != null){
            Log.d("Events array", " != null, success")
            recycler_view.apply {
                recycler_view.layoutManager = LinearLayoutManager(context)
                eventsAdapter = EventsAdapter(eventsArray,mListener,context)
                recycler_view.adapter = eventsAdapter
            }
        }
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnListFragmentInteractionListener {
        fun onCreateView(
            inflater: LayoutInflater?, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View?

        // TODO: Update argument type and name
        @Throws(
            java.lang.InstantiationException::class, IllegalAccessException::class,
            JSONException::class
        )
        fun onListFragmentInteraction(item: JSONObject?)
    }
}