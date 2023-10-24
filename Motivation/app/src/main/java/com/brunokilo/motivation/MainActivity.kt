package com.brunokilo.motivation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.brunokilo.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            buttonNewphrase.setOnClickListener {
            }
        }
        // Esconder a barra de navegação
        supportActionBar?.hide()
        handleUserName()

    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString("USER_NAME")
        binding?.textName?.text = "Olá, $name!"
    }

}