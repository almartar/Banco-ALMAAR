package com.albertomarti.banco_almaar.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.albertomarti.banco_almaar.R
import com.albertomarti.banco_almaar.databinding.CardviewCuentaBinding
import com.example.bancoapiprofe.pojo.Cuenta


class adapterCuentas(private val itemList: ArrayList<Cuenta>) : RecyclerView.Adapter<adapterCuentas.ViewHolder>() {
    // Usar ItemLayoutBinding como parámetro del ViewHolder
    inner class ViewHolder(val binding: CardviewCuentaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflar el layout usando el binding
        val binding = CardviewCuentaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        // Usar el binding para acceder a los elementos de la vista
        holder.binding.textView2.text = item.getNumeroCuenta().toString()
        holder.binding.textView3.text = item.getSaldoActual().toString()
        if(item.getSaldoActual()!!.toFloat() > 0f) holder.binding.textView3.setTextColor(Color.GREEN)
        else if (item.getSaldoActual()!!.toFloat() < 0f) holder.binding.textView3.setTextColor(Color.RED)
    }

    override fun getItemCount() = itemList.size
}