package com.example.scancial

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.scancial.ui.HomeFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener,
    View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment())
    }
    private fun loadFragment(fragment: Fragment?): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarColor(fragment)
        }
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_main, fragment)
                .commit()
            return true
        }
        return false
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun setStatusBarColor(fragment: Fragment?) {
        if(fragment is HomeFragment){
            window.statusBarColor = getColor(R.color.silver100)
            var flags = window.decorView.systemUiVisibility
            flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            window.decorView.systemUiVisibility = flags
        }else{
            window.statusBarColor = getColor(R.color.white)
            window.decorView.systemUiVisibility =  View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}