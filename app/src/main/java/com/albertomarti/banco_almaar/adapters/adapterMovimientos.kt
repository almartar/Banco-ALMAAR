package com.albertomarti.banco_almaar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albertomarti.banco_almaar.databinding.CardviewCuentaBinding
import com.albertomarti.banco_almaar.databinding.ListElementMovimientosBinding
import com.example.bancoapiprofe.pojo.Cuenta
import com.example.bancoapiprofe.pojo.Movimiento

class adapterMovimientos(private val itemList: ArrayList<Movimiento>)  : RecyclerView.Adapter<adapterMovimientos.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListElementMovimientosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.textView5.text = item.getDescripcion()
        holder.binding.textView6.text = item.getFechaOperacion().toString() + " Importe:" + item.getImporte()
    }


    // Usar ItemLayoutBinding como parámetro del ViewHolder
    inner class ViewHolder(val binding: ListElementMovimientosBinding) : RecyclerView.ViewHolder(binding.root)
}