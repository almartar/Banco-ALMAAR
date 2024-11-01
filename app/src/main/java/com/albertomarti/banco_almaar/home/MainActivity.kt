package com.albertomarti.banco_almaar.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.albertomarti.banco_almaar.R
import com.albertomarti.banco_almaar.databinding.ActivityLoginBinding
import com.albertomarti.banco_almaar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        val dni = intent.getStringExtra("DNI_k")
        binding.dniId.setText(dni)
    }

}