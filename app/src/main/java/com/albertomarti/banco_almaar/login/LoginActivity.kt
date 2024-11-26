package com.albertomarti.banco_almaar.login

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.albertomarti.banco_almaar.AppPersistant
import com.albertomarti.banco_almaar.FragmentBlue
import com.albertomarti.banco_almaar.R
import com.albertomarti.banco_almaar.databinding.ActivityLoginBinding
import com.albertomarti.banco_almaar.fragmentRojo
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

        binding.bb.setOnClickListener{
            openHome(binding.userEditTextId.text.toString())
        }
    }

    private fun setUpView() {
        //val dniSample = "11111111A"
        //val passwordSample = "1234"

    }




    private fun loadFragment(fragment: Fragment) {
        // Iniciar una transacción de fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null) // Agregar a la pila de retroceso
        transaction.commit()
    }




    // COPY_ALBERTO
    // Sobreescribir la función completa en tu código
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