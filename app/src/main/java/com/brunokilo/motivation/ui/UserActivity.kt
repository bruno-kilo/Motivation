package com.brunokilo.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.brunokilo.motivation.R
import com.brunokilo.motivation.infra.MotivationConstants
import com.brunokilo.motivation.infra.SecurityPreferences
import com.brunokilo.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private var binding: ActivityUserBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater).apply {
            setContentView(root)
            buttonSave.setOnClickListener {
             handleSave()
            }
        }
        supportActionBar?.hide()
        verifyUserName()
    }


    private fun handleSave() {
        val name = binding?.editName?.text.toString()
        if (name.isNotEmpty()) {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name != null) {
            if (name.isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(MotivationConstants.KEY.USER_NAME, name)
                startActivity(intent)
                finish()
            }
        }
    }
}