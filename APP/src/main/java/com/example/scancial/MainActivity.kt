package com.example.scancial

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.scancial.databinding.ActivityMainBinding
import com.example.scancial.ui.HomeFragment
import com.example.scancial.ui.ProfileFragment
import com.example.scancial.ui.ScheduleFragment
import com.example.scancial.ui.ShopFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener,
    View.OnClickListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())
        initView()
    }
    private fun initView() = with(binding) {
        with(bnv.menu) {
            selectedItemId = R.id.menuHome
            setOnItemSelectedListener(this@MainActivity)
        }
        fab.setOnClickListener(this@MainActivity)

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
    @Suppress("DEPRECATION")
    private fun setStatusBarColor(fragment: Fragment?) {
            window.statusBarColor = getColor(R.color.teal)
            var flags = window.decorView.systemUiVisibility
            flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            window.decorView.systemUiVisibility = flags
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuHome -> loadFragment(HomeFragment())
            R.id.menuShop -> loadFragment((ShopFragment()))
            R.id.menuUser -> loadFragment(ProfileFragment())
            R.id.menuSchedule -> loadFragment(ScheduleFragment())

            else -> false
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}