package com.example.gniar_animelist.menus

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gniar_animelist.R
import com.example.gniar_animelist.retrofitest.helpers.AnimeAdapter
import com.example.gniar_animelist.retrofitest.models.Anime
import com.example.gniar_animelist.retrofitest.services.AnimeService
import com.example.gniar_animelist.retrofitest.services.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.runBlocking
import
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        btnFetch.setOnClickListener{

            runBlocking {
                JikanKt
            }
        }
    }

//    private fun loadCountries() {
//        //initiate the service
//        val destinationService  = ServiceBuilder.buildService(AnimeService::class.java)
//        val requestCall =destinationService.getAffectedAnimeList()
//        Toast.makeText(activity, "Button clicked", Toast.LENGTH_SHORT).show()
//        //make network call asynchronously
//        requestCall.enqueue(object : Callback<List<Anime>> {
//            override fun onResponse(call: Call<List<Anime>>, response: Response<List<Anime>>) {
//                Log.d("Response", "onResponse: ${response.body()}")
////                if (response.isSuccessful){
////                    val countryList  = response.body()!!
////                    Log.d("Response", "countrylist size : ${countryList.size}")
////
////                    rvListAnime.apply {
////                        setHasFixedSize(true)
////                        layoutManager = LinearLayoutManager(activity)
////                        adapter = AnimeAdapter(response.body()!!)
////                    }
////                }else{
////                    Toast.makeText(activity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
////                }
//            }
//            override fun onFailure(call: Call<List<Anime>>, t: Throwable) {
//                Toast.makeText(activity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
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