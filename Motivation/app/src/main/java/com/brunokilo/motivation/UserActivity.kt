package com.brunokilo.motivation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.brunokilo.motivation.databinding.ActivityMainBinding
import com.brunokilo.motivation.databinding.ActivityUserBinding
import java.security.Security

class UserActivity : AppCompatActivity() {

    private  var binding: ActivityUserBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater).apply {
            setContentView(root)
            buttonSave.setOnClickListener {
             handleSave()
            }
        }
        supportActionBar?.hide()
    }


    private fun handleSave() {
        val name = binding?.editName?.text.toString()
        if (name != "") {

            SecurityPreferences(this).storeString("USER_NAME", name)

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }

}