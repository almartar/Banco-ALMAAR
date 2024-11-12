package com.albertomarti.banco_almaar.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.albertomarti.banco_almaar.R
import com.albertomarti.banco_almaar.databinding.ActivityLoginBinding
import com.albertomarti.banco_almaar.databinding.ActivityMainBinding
import com.albertomarti.banco_almaar.options.PosicionGlobalActivity
import com.albertomarti.banco_almaar.options.TransferActivity

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

        binding.transfersButtonId.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }

        binding.globalPositiconButtonId.setOnClickListener{
            val intent = Intent(this, PosicionGlobalActivity::class.java)
            startActivity(intent)
        }
    }
}