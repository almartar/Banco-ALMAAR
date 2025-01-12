package com.albertomarti.banco_almaar.movements

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertomarti.banco_almaar.AppPersistant
import com.albertomarti.banco_almaar.R
import com.albertomarti.banco_almaar.adapters.adapterMovimientos
import com.albertomarti.banco_almaar.databinding.ActivityMovementsBinding
import com.example.bancoapiprofe.pojo.Cuenta
import com.example.bancoapiprofe.pojo.Movimiento
import com.google.android.material.bottomnavigation.BottomNavigationView

class MovementsActivity : AppCompatActivity() {
    lateinit var listaCuentas: ArrayList<Cuenta>
    lateinit var listaMovimientos: ArrayList<Movimiento>
    var cuenta: Cuenta? = null

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
                cuenta = findCuenta(selectedItem)
                listaMovimientos = AppPersistant.bancoAPIprofe!!.getMovimientos(cuenta) as ArrayList<Movimiento>

                binding.recyclerViewMovimientos.layoutManager = LinearLayoutManager(this@MovementsActivity)
                binding.recyclerViewMovimientos.adapter = adapterMovimientos(listaMovimientos)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }



        // Implementación de bottom Navigation View -----------------------------------------
        val bottomNavigationView : BottomNavigationView = binding.bottomNavView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            listaMovimientos = ArrayList()

            when(item.itemId){
                R.id.allTypes ->{
                    listaMovimientos = AppPersistant.bancoAPIprofe!!.getMovimientos(cuenta) as ArrayList<Movimiento>
                    binding.recyclerViewMovimientos.adapter = adapterMovimientos(listaMovimientos)
                    true
                }
                R.id.type0 ->{
                    listaMovimientos = AppPersistant.bancoAPIprofe!!.getMovimientosTipo(cuenta, 0) as ArrayList<Movimiento>
                    binding.recyclerViewMovimientos.adapter = adapterMovimientos(listaMovimientos)
                    true
                }
                R.id.type1 ->{
                    listaMovimientos = AppPersistant.bancoAPIprofe!!.getMovimientosTipo(cuenta, 1) as ArrayList<Movimiento>
                    binding.recyclerViewMovimientos.adapter = adapterMovimientos(listaMovimientos)
                    true
                }
                R.id.type2 ->{
                    listaMovimientos = AppPersistant.bancoAPIprofe!!.getMovimientosTipo(cuenta, 2) as ArrayList<Movimiento>
                    binding.recyclerViewMovimientos.adapter = adapterMovimientos(listaMovimientos)
                    true
                }
                else ->{
                    false
                }
            }
        }
        // ----------------------------------------------------------------------------------





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




