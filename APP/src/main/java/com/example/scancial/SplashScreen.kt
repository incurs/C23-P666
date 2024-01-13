package com.example.scancial

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.scancial.databinding.ActivitySplashScreenBinding
import com.example.scancial.ui.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarColor(fragment = null)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Pengguna sudah login, redirect ke MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            // Pengguna belum login, tetap di SplashScreen
            binding.btnStart.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    @Suppress("DEPRECATION")
    private fun setStatusBarColor(fragment: Fragment?) {
        window.statusBarColor = getColor(R.color.teal)
        var flags = window.decorView.systemUiVisibility
        flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        window.decorView.systemUiVisibility = flags
    }
}