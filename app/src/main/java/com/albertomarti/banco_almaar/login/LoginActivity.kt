package com.albertomarti.banco_almaar.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.albertomarti.banco_almaar.AppPersistant
import com.albertomarti.banco_almaar.R
import com.albertomarti.banco_almaar.databinding.ActivityLoginBinding
import com.albertomarti.banco_almaar.home.MainActivity
import com.example.bancoapiprofe.pojo.Cliente


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        AppPersistant.initialize(this)
    }

    private fun setUpView() {
        //val dniSample = "11111111A"
        //val passwordSample = "1234"

        binding.signInButtonId.setOnClickListener {
            val dni = binding.userEditTextId.text.toString()
            val password = binding.passwordEditTextId.text.toString()

            if (dni.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, getString(R.string.empty_data), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                var user : Cliente = Cliente()
                user.setNif(dni)
                user.setClaveSeguridad(password)
                var userAUX: Cliente? = AppPersistant.bancoAPIprofe!!.login(user)
                if (userAUX != null){
                    AppPersistant.actualUser = userAUX
                    openHome(AppPersistant.actualUser!!.getNif()!!)
                }
                else{
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }
                return@setOnClickListener
            }
            Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_SHORT).show()
        }

        binding.exitButtonId.setOnClickListener {
            finish()
        }
    }


    private fun openHome(dni: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("DNI_k", dni)
        startActivity(intent)
    }
}