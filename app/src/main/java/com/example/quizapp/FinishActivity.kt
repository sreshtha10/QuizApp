package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityFinishBinding


class FinishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinishBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinishBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val name = intent.getStringExtra("EXTRA_NAME")

        val score = intent.getStringExtra("EXTRA_SCORE")

        binding.tvFinish.text = "${name}, your score is ${score}"


        binding.btnPlayAgain.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}