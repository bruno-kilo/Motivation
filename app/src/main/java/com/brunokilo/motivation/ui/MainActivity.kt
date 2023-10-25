package com.brunokilo.motivation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.brunokilo.motivation.R
import com.brunokilo.motivation.databinding.ActivityMainBinding
import com.brunokilo.motivation.utils.MotivationEnum
import com.devmasterteam.motivation.repository.Mock

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var typeState = MotivationEnum.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val safeBinding = ActivityMainBinding.inflate(layoutInflater).apply {
            binding = this
        }

        setContentView(safeBinding.root)
        supportActionBar?.hide()

        initialize()
    }

    private fun initialize() {
        handleUserName()
        setupLayout()
    }

    private fun setupLayout() {
        val colorToApply = ContextCompat.getColor(this@MainActivity, R.color.white)
        setupAllButtonClick(colorToApply)
        binding?.apply {
            binding?.apply {
                buttonNewphrase.setOnClickListener { handleNextPhrase() }

                imageAll.setOnClickListener { setupAllButtonClick(colorToApply) }
                imageHappy.setOnClickListener { setupHappyButton(colorToApply) }
                imageSunny.setOnClickListener { setupSunnyButton(colorToApply) }
            }
        }
    }

    private fun setupAllButtonClick(colorToApply: Int) {
        typeState = MotivationEnum.ALL
        binding?.apply {
            clearColorFilter()
            imageAll.setColorFilter(colorToApply)
            showToast("Todas as frases")
        }
    }

    private fun setupHappyButton(colorToApply: Int) {
        typeState = MotivationEnum.HAPPY
        binding?.apply {
            clearColorFilter()
            imageHappy.setColorFilter(colorToApply)
            showToast("Frases que motivam")
        }
    }

    private fun setupSunnyButton(colorToApply: Int) {
        typeState = MotivationEnum.SUNNY
        binding?.apply {
            clearColorFilter()
            imageSunny.setColorFilter(colorToApply)
            showToast("Frases que iluminam")
        }
    }

    private fun clearColorFilter() {
        binding?.apply {
            imageAll.clearColorFilter()
            imageSunny.clearColorFilter()
            imageHappy.clearColorFilter()
        }
    }

    private fun handleUserName() {
        val name = intent.getStringExtra(PARAMETER_NAME)
        if (!name.isNullOrEmpty()) {
            binding?.textName?.text = "Olá, $name!"
        }
    }


    private fun handleNextPhrase() {
        binding?.textPhrase?.text = Mock().getPhrase(typeState)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val PARAMETER_NAME = "PARAMETER_NAME"

        fun getIntent(context: Context, name: String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(PARAMETER_NAME, name)
            return intent
        }
    }
}
