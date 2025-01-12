package com.albertomarti.banco_almaar.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.albertomarti.banco_almaar.R
import com.albertomarti.banco_almaar.databinding.ActivityLoginBinding
import com.albertomarti.banco_almaar.databinding.ActivityMainBinding
import com.albertomarti.banco_almaar.movements.MovementsActivity
import com.albertomarti.banco_almaar.options.PosicionGlobalActivity
import com.albertomarti.banco_almaar.options.TransferActivity
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        drawerLayout = binding.drawerLayout!!
        val toolBar: Toolbar = binding.toolbar!!
        val navigationView: NavigationView = binding.navigationView!!

        setSupportActionBar(toolBar)
        toolBar.setNavigationIcon(R.drawable.menuicon)
        toolBar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START) // Se abre a la izquierda (START) o a la derecha (END)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.Nav_Home -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.Nav_Movements -> {
                    val intent = Intent(this, MovementsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        val dni = intent.getStringExtra("DNI_k")
        binding.dniId.setText(dni)

        binding.transfersButtonId.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }

        binding.globalPositiconButtonId.setOnClickListener{
            val intent = Intent(this, PosicionGlobalActivity::class.java)
            startActivity(intent)
        }

        binding.movementsButtonId.setOnClickListener{
            val intent = Intent(this, MovementsActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.Nav_Home -> {
                true
            }
            R.id.Nav_Cajeros -> {
                true
            }
            R.id.Nav_Movements -> {
                true
            }
            R.id.Nav_Transfers -> {
                true
            }
            R.id.Nav_Configuration -> {
                true
            }
            R.id.Nav_ChangePass -> {
                true
            }
            else -> false
        }
    }
}