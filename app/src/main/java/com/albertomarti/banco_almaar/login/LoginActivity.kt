package com.albertomarti.banco_almaar.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.albertomarti.banco_almaar.R
import com.albertomarti.banco_almaar.databinding.ActivityLoginBinding
import com.albertomarti.banco_almaar.home.MainActivity

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        val dniSample = "20831271"
        val passwordSample = "12345678"
        binding.signInButtonId.setOnClickListener {
            val dni = binding.userEditTextId.text.toString()
            val password = binding.passwordEditTextId.text.toString()
            if (dni.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, getString(R.string.empty_data), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (dni == dniSample && password == passwordSample) {
                openHome(dni)
                return@setOnClickListener
            }
            Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_SHORT).show()
        }

        binding.exitButtonId.setOnClickListener {
            this.finishAffinity();
        }
    }


    private fun openHome(dni: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("DNI_k", dni)
        startActivity(intent)
    }
}