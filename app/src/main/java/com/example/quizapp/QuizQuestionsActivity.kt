package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityQuizQuestionsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val questionHandler = intent.getSerializableExtra("EXTRA_QuestionHandler") as QuestionHandler

        /*Toast.makeText(
            this,
            questionHandler.results.toString(),
            Toast.LENGTH_LONG
        ).show()
        */






    }
}