package com.example.scancial.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.scancial.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

@Suppress("DEPRECATION")
class RegisterActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var binding : ActivityRegisterBinding
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Pendaftaran...")

        binding.tvAReady.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            val username = binding.editName.text.toString()

            if (email.isEmpty()) {
                binding.editEmail.error = "Email Harus Diisi"
                binding.editEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.editEmail.error = "Email Tidak Valid"
                binding.editEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.editPassword.error = "Password Harus Diisi"
                binding.editPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.editPassword.error = "Password Minimal 6 Karakter"
                binding.editPassword.requestFocus()
                return@setOnClickListener
            }

            if (username.isEmpty()) {
                binding.editName.error = "Nama Pengguna Harus Diisi"
                binding.editName.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email, password, username)
        }
    }

    private fun RegisterFirebase(email: String, password: String, username: String) {
        progressDialog.show()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                progressDialog.dismiss()
                if (task.isSuccessful) {
                    Snackbar.make(binding.root, "Akun Berhasil Dibuat", Snackbar.LENGTH_LONG)
                    val firebaseUser = auth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(username)
                        .build()

                    firebaseUser?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { updateProfileTask ->
                            if (updateProfileTask.isSuccessful) {
                                Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Snackbar.make(binding.root, "${task.exception?.message}", Snackbar.LENGTH_SHORT).show()
                }
            }
    }

}