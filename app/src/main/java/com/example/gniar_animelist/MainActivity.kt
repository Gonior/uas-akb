package com.example.gniar_animelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.gniar_animelist.menus.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val myListFragment = MyListFragment()
    private val aboutFragment = AboutFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()
        replaceFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> replaceFragment(homeFragment);
                R.id.search_fragment -> replaceFragment(searchFragment);
                R.id.myList -> replaceFragment(myListFragment);
                R.id.about -> replaceFragment(aboutFragment);
                else -> false
            }
        }

    }
    private fun replaceFragment(fragment : Fragment): Boolean {
        if(fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.flFragment, fragment)
            transaction.commit()

        }
        return true
    }

}