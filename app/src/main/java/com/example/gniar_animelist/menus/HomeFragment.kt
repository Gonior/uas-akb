package com.example.gniar_animelist.menus

import Season_Base
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gniar_animelist.R
import com.example.gniar_animelist.retrofitest.helpers.AnimeAdapter
import com.example.gniar_animelist.retrofitest.services.AnimeService
import com.example.gniar_animelist.retrofitest.services.ServiceBuilder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var selectedSeason: String = "summer"
    private var selectedYear : Int = 2021

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val seasons = arrayOf("winter", "spring", "summer", "fall")
        val years = arrayOf(2021,2020,2019,2018,2017,2016,2015,2014)

        var adapterYear = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, years ) }
        var adapterSeason = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, seasons ) }

        spinnerYear.adapter = adapterYear
        spinnerSeason.adapter = adapterSeason
        spinnerYear.setSelection(0)
        spinnerYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedYear = years[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinnerSeason.setSelection(2)
        spinnerSeason.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedSeason = seasons[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        fetchApi("https://api.jikan.moe/v3/season")

        btnFetch.setOnClickListener{
            fetchApi("https://api.jikan.moe/v3/season/${selectedYear}/${selectedSeason}")

        }
    }

    private fun fetchApi(url : String) {
        progressBarHome.visibility = View.VISIBLE
        val request = Request.Builder().url(url).build()

        val client  = OkHttpClient()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("myFetch", "something went wrong")
                progressBarHome.visibility = View.GONE
                Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response?.body()?.string()

                val animes = Gson().fromJson(body, Season_Base::class.java)

                Log.d("mySearch", "${animes.anime}")
                activity?.runOnUiThread {
                    titleHome.text = "List Anime -" + " $selectedSeason $selectedYear".toUpperCase()
                    progressBarHome.visibility = View.GONE
                    rvListAnime.layoutManager = LinearLayoutManager(activity)
                    rvListAnime.adapter = AnimeAdapter(animes.anime)
                }


            }


        })

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}