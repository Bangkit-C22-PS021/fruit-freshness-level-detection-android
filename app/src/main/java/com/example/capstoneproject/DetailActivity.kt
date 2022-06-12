package com.example.capstoneproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstoneproject.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val NAME = "name"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val person = intent.getParcelableExtra<Character>(NAME) as Character

        binding.imageView2.setImageResource(person.photo)
        binding.textView2.text = person.description
        binding.textView3.text = person.name

        binding.cameraButton.setOnClickListener {
            val intent  = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }

        binding.bottomNavigation.setOnItemReselectedListener { item ->
            when (item.itemId){
                R.id.home ->{
                    val intent  = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.history ->{
                    val intent  = Intent(this, ResultActivity2::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}