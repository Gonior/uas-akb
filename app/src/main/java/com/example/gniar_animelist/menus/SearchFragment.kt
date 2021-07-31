package com.example.gniar_animelist.menus

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gniar_animelist.R
import com.example.gniar_animelist.adapter.ListAnimeSearchAdapter
import com.example.gniar_animelist.retrofitest.helpers.AnimeAdapter
import com.example.gniar_animelist.retrofitest.models.Search_base
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_search.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



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
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etQuery.setText("")

        etQuery.setOnKeyListener(View.OnKeyListener {v, keycode , event ->
            var query = etQuery.text
            if (keycode == KeyEvent.KEYCODE_ENTER && event.action ==  KeyEvent.ACTION_UP) {
                if (query.length >= 3) {
                    fetchApi("https://api.jikan.moe/v3/search/anime?q=$query","$query" )
                } else {
                    Toast.makeText(activity, "Must greater than 3 word", Toast.LENGTH_SHORT).show()

                }
                return@OnKeyListener true
            }
            false
        })
        btnRefreshSearchAnime.setOnClickListener {
            var query = etQuery.text
            fetchApi("https://api.jikan.moe/v3/search/anime?q=$query","$query" )
        }
    }
    private fun fetchApi(url : String, query : String) {
        etNotif.visibility = View.GONE
        containerErrorSearchAnime.visibility = View.GONE
        progressBarSearch.visibility = View.VISIBLE

        val request = Request.Builder().url(url).build()

        val client  = OkHttpClient()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                activity?.runOnUiThread {
                    progressBarSearch.visibility = View.GONE
                    containerErrorSearchAnime.visibility = View.VISIBLE
                    Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response?.body()?.string()

                val animes = Gson().fromJson(body, Search_base::class.java)
                activity?.runOnUiThread {
                    containerResultQuery.visibility = View.VISIBLE

                    tvResultSearchQuery.text = query
                    rvListAnimeSearch.visibility = View.VISIBLE
                    if (animes.results.isNotEmpty()) {
                        progressBarSearch.visibility = View.GONE
                        rvListAnimeSearch.layoutManager = GridLayoutManager(activity,2)
                        rvListAnimeSearch.adapter = ListAnimeSearchAdapter(animeList = animes.results)
                    } else {
                        etNotif.visibility = View.VISIBLE
                        etNotif.text = "Result from $query not Found!"
                    }
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
         * @return A new instance of fragment RecommendationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}