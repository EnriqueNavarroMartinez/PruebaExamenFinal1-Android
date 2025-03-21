package com.example.examenfinal1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.examenfinal1.databinding.ActivityMainBinding
import com.example.examenfinal1.fragment.ApiFragment
import com.example.examenfinal1.fragment.InicioFragment
import com.example.examenfinal1.fragment.RoomFragment

class MainActivity : AppCompatActivity() {

    //Primero hay que declarar este lateinit para el binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializamos con el HomeFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, InicioFragment())
            .commit()
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.navigation_room -> {
                    // Acción al seleccionar Home
                    replaceFragment(RoomFragment())
                }
                R.id.navigation_api -> {
                    // Acción al seleccionar Dashboard
                    replaceFragment(ApiFragment())
                }
            }
            false
        }

    }

    //Funcion para ir cambiando de fragments
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }


}