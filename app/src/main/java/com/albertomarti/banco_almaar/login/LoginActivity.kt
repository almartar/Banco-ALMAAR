package com.albertomarti.banco_almaar.login

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.albertomarti.banco_almaar.AppPersistant


import com.albertomarti.banco_almaar.databinding.ActivityLoginBinding

import com.albertomarti.banco_almaar.home.MainActivity
import com.example.bancoapiprofe.dao.ClienteDAO
import com.example.bancoapiprofe.pojo.Cliente


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        AppPersistant.initialize(this)


        binding.signInButtonId.setOnClickListener{
            openHome(binding.userEditTextId.text.toString())
        }
    }

    private fun setUpView() {
        //val dniSample = "11111111A"
        //val passwordSample = "1234"

    }









    private fun openHome(dni: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("DNI_k", dni)

        val cliente = Cliente(
            id = 0,
            nif = dni,
            nombre = "",
            apellidos = "",
            claveSeguridad = "",
            email = ""
        )
        AppPersistant.actualUser = ClienteDAO().search(cliente) as Cliente?
        startActivity(intent)
    }
}