package com.albertomarti.banco_almaar.options

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.albertomarti.banco_almaar.AppPersistant
import com.albertomarti.banco_almaar.R
import com.albertomarti.banco_almaar.databinding.ActivityLoginBinding
import com.albertomarti.banco_almaar.databinding.ActivityTransferBinding
import com.example.bancoapiprofe.pojo.Cuenta

class TransferActivity : AppCompatActivity() {
    lateinit var binding: ActivityTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var listaCuentas = AppPersistant.bancoAPIprofe!!.getCuentas(AppPersistant.actualUser) as ArrayList<Cuenta>
        var listaCuentasIBAN = listaCuentas.map { it.getNumeroCuenta() } as ArrayList<String>
        var mutableList: MutableList<*> = listaCuentasIBAN as ArrayList
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mutableList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        binding.spinnerCuentasPropiasTo.adapter = adapter

        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListOf("€","$") as List<Any?>)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMoneda.adapter = adapter

        binding.radioGroupOpciones.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radioButton_propia -> {
                    binding.spinnerCuentasPropiasTo.visibility = View.VISIBLE
                    binding.editTextNumCuentaAjena.visibility = View.INVISIBLE
                }
                R.id.radioButton_ajena -> {
                    binding.spinnerCuentasPropiasTo.visibility = View.INVISIBLE
                    binding.editTextNumCuentaAjena.visibility = View.VISIBLE
                }
            }
        }


        binding.buttonCANCEL.setOnClickListener{
            binding.textCantidad.setText("")
            binding.editTextNumCuentaAjena.setText("")
            binding.enviarJustificante.isChecked = false
        }
        binding.buttonSEND.setOnClickListener{
            var cuentaFROM = (binding.spinner.selectedView as TextView).text.toString()
            var cuentaTO = ""
            var msgTipoCuenta = ""
            if(binding.radioButtonPropia.isChecked){
                cuentaTO = (binding.spinnerCuentasPropiasTo.selectedView as TextView).text.toString()
                msgTipoCuenta = "A cuenta propia:"
            }
            else{
                cuentaTO = binding.editTextNumCuentaAjena.text.toString()
                msgTipoCuenta = "A cuenta ajena:"
            }

            var cantidad = binding.textCantidad.text.toString()
            var divisa = (binding.spinnerMoneda.selectedView as TextView).text.toString()
            var enviarJustificante = false
            var enviarJustificanteSTRING = "No enviar justificante"
            if(binding.enviarJustificante.isChecked){
                enviarJustificante = true
                enviarJustificanteSTRING = "Enviar justificante"
            }

            var msg: String = """Cuenta origen:
                                $cuentaFROM
                                $msgTipoCuenta
                                $cuentaTO
                                Importe: $cantidad$divisa
                                $enviarJustificanteSTRING""".trimIndent()
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }
    }

}