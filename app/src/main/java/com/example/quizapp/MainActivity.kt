package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.quizapp.databinding.ActivityMainBinding
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var questionHandler: QuestionHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




        binding.btnStart.setOnClickListener {
            if (binding.etName.text.isEmpty()) {
                Toast.makeText(
                    this, "Please enter your name", Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            Intent(this, QuizQuestionsActivity::class.java).also {
                startActivity(it)
            }
        }





        val url = "https://opentdb.com/api.php?amount=10&type=multiple"
        val requestQueue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(Request.Method.GET,url,

            {
                val gson= Gson()
                questionHandler = gson.fromJson(it,QuestionHandler::class.java)

                Log.d("CODE",questionHandler.results.toString())


            },

            {
                Log.d("Code","error")
                // display toast
            }

            )


        requestQueue.add(stringRequest)







    }

}


