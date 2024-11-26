package com.albertomarti.banco_almaar.movements

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertomarti.banco_almaar.AppPersistant
import com.albertomarti.banco_almaar.R
import com.albertomarti.banco_almaar.adapters.adapterCuentas
import com.albertomarti.banco_almaar.adapters.adapterMovimientos
import com.albertomarti.banco_almaar.databinding.ActivityMovementsBinding
import com.example.bancoapiprofe.pojo.Cuenta
import com.example.bancoapiprofe.pojo.Movimiento

class MovementsActivity : AppCompatActivity() {
    lateinit var listaCuentas: ArrayList<Cuenta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMovementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listaCuentas = AppPersistant.bancoAPIprofe!!.getCuentas(AppPersistant.actualUser) as ArrayList<Cuenta>
        val newListaCuentas = ArrayList<String>()
        for(cuenta in listaCuentas){
            newListaCuentas.add(cuenta.getNumeroCuenta()!!)
        }

        binding.spinnerCuentasMovimientos.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, newListaCuentas)
        binding.spinnerCuentasMovimientos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position) as String
                val cuenta: Cuenta? = findCuenta(selectedItem);
                val movimientos: ArrayList<Movimiento> = AppPersistant.bancoAPIprofe!!.getMovimientos(cuenta) as ArrayList<Movimiento>

                binding.recyclerViewMovimientos.layoutManager = LinearLayoutManager(this@MovementsActivity)
                binding.recyclerViewMovimientos.adapter = adapterMovimientos(movimientos)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }




    fun findCuenta(IBAN: String): Cuenta?{
        for(cuenta in listaCuentas){
            if(cuenta.getNumeroCuenta() == IBAN){
                return cuenta
            }
        }
        return null
    }
}




