package com.example.scancial.ui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.scancial.MainActivity
import com.example.scancial.R
import com.example.scancial.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var auth : FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Masuk...")

        binding.tvSignUp.setOnClickListener{
            val intent = Intent(
                this, RegisterActivity::class.java
            )
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            if (email.isEmpty()){
                binding.editEmail.error = "Email Harus Diisi"
                binding.editEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.editPassword.error = "Email Tidak Valid"
                binding.editPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.editPassword.error = "Password Harus Diisi"
                binding.editPassword.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email,password)
        }
        binding.btnLoginGoogle.setOnClickListener{
            googleSignIn()
        }
    }

    private fun LoginFirebase(email: String, password: String) {
        progressDialog.show()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                progressDialog.dismiss()
                if (it.isSuccessful) {
                    Toast.makeText(this, "Selamat datang $email", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Snackbar.make(binding.root, "${it.exception?.message}", Snackbar.LENGTH_SHORT).show()
                }
            }
    }
    private fun googleSignIn() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account?.idToken)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google Sign-In Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        progressDialog.show()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                progressDialog.dismiss()
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Selamat datang ${user?.displayName}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Snackbar.make(binding.root, "${task.exception?.message}", Snackbar.LENGTH_SHORT).show()
                }
            }
    }
}