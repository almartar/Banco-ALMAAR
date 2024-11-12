package com.albertomarti.banco_almaar.options

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertomarti.banco_almaar.AppPersistant
import com.albertomarti.banco_almaar.R
import com.albertomarti.banco_almaar.adapters.adapterCuentas
import com.albertomarti.banco_almaar.databinding.ActivityLoginBinding
import com.albertomarti.banco_almaar.databinding.ActivityPosicionGlobalBinding
import com.albertomarti.banco_almaar.databinding.CardviewCuentaBinding
import com.example.bancoapiprofe.pojo.Cuenta

class PosicionGlobalActivity : AppCompatActivity() {
    lateinit var binding: ActivityPosicionGlobalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPosicionGlobalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var listaCuentas = AppPersistant.bancoAPIprofe!!.getCuentas(AppPersistant.actualUser) as ArrayList<Cuenta>
        val adapter: adapterCuentas = adapterCuentas(listaCuentas)
        binding.recyclerViewCuentas.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCuentas.adapter = adapter
    }
}