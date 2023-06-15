package com.example.scancial

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.scancial.databinding.ActivityMainBinding
import com.example.scancial.ui.CameraActivity
import com.example.scancial.ui.HomeFragment
import com.example.scancial.ui.ProfileFragment
import com.example.scancial.ui.ScheduleFragment
import com.example.scancial.ui.ShopFragment
import com.example.scancial.utils.createCustomTempFile
import com.example.scancial.utils.uriToFile
import com.google.android.material.navigation.NavigationBarView
import java.io.File

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
//    @Suppress("DEPRECATION")
//    private fun setStatusBarColor(fragment: Fragment?) {
//        fragment?.activity?.window?.apply {
//            statusBarColor = Color.TRANSPARENT
//            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        }
//    }

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

    override fun onClick(v: View?) {
        when (v) {
            binding.fab -> {
                val cameraIntent = Intent(this@MainActivity, CameraActivity::class.java )
                startActivity(cameraIntent)
            }
        }
    }
}