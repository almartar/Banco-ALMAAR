package com.albertomarti.banco_almaar

import android.content.Context
import com.example.bancoapiprofe.bd.MiBancoOperacional
import com.example.bancoapiprofe.pojo.Cliente

object AppPersistant {
    var bancoAPIprofe : MiBancoOperacional? = null
    private var isConnected: Boolean = false
    var actualUser: Cliente? = null

    fun initialize(context: Context){
        bancoAPIprofe = MiBancoOperacional.getInstance(context)
        if(bancoAPIprofe != null){
            isConnected = true
        }
    }

    fun isConnected(): Boolean{
        return isConnected
    }
}