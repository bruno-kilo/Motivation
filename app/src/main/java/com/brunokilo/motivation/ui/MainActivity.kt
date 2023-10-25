package com.brunokilo.motivation.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.brunokilo.motivation.R
import com.brunokilo.motivation.infra.MotivationConstants
import com.brunokilo.motivation.infra.SecurityPreferences
import com.brunokilo.motivation.databinding.ActivityMainBinding
import com.devmasterteam.motivation.repository.Mock

class MainActivity : AppCompatActivity() {
    private var categoryId = MotivationConstants.FILTER.ALL
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            val viewsToColorize = arrayOf(imageAll, imageHappy, imageSunny)
            val colorToApply = ContextCompat.getColor(this@MainActivity, R.color.white)

            val clickListener = View.OnClickListener { view ->
                when (view) {
                    buttonNewphrase -> {
                        handleNextPhrase()
                    }
                    imageAll -> {
                        imageAll.setColorFilter(colorToApply)
                        imageHappy.clearColorFilter()
                        imageSunny.clearColorFilter()
                        categoryId = MotivationConstants.FILTER.ALL
                        showToast("Todas as frases")

                    }
                    imageHappy -> {
                        imageAll.clearColorFilter()
                        imageHappy.setColorFilter(colorToApply)
                        imageSunny.clearColorFilter()
                        categoryId = MotivationConstants.FILTER.HAPPY
                        showToast("Frases que motivam")
                    }
                    imageSunny -> {
                        imageAll.clearColorFilter()
                        imageHappy.clearColorFilter()
                        imageSunny.setColorFilter(colorToApply)
                        categoryId = MotivationConstants.FILTER.SUNNY
                        showToast("Frases que iluminam")
                    }
                }
            }

            buttonNewphrase.setOnClickListener(clickListener)
            viewsToColorize.forEach { imageView ->
                imageView.setOnClickListener(clickListener)
            }

            supportActionBar?.hide()

        }
        handleUserName()

    }

    private fun handleUserName() {
        val name = intent.getStringExtra(PARAMETER_NAME)
        if (!name.isNullOrEmpty()) {
            binding?.textName?.text = "Olá, $name!"
        }
    }


    private fun handleNextPhrase() {
        binding?.textPhrase?.text = Mock().getPhrase(categoryId)
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

fun View.clearColorFilter() {
    this.clearColorFilter()
}
