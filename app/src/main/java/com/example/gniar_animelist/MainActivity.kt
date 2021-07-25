package com.example.gniar_animelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.gniar_animelist.menus.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val recommendationFragment = RecommendationFragment()
    private val myListFragment = MyListFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()
        replaceFragment(homeFragment)
        bottomNavigationView.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.recommendation -> replaceFragment(recommendationFragment)
                R.id.myList -> replaceFragment(myListFragment)
            }
        }
    }
    private fun replaceFragment(fragment : Fragment) {
        if(fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.flFragment, fragment)
            transaction.commit()
        }

    }

}