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




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val intent = Intent(this,QuizQuestionsActivity::class.java)

        val name = binding.etName.text.toString()


        fetchFromAPI{
            intent.putExtra("EXTRA_QuestionHandler",it)

            Log.d("apiResult",it.results.toString())

        }






        binding.btnStart.setOnClickListener {
            if (binding.etName.text.isEmpty()) {
                Toast.makeText(
                    this, "Please enter your name", Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            intent.putExtra("EXTRA_NAME",name)
            startActivity(intent)
            finish()

        }








    }








    private fun fetchFromAPI(callback:(QuestionHandler)->Unit){

        val url = "https://opentdb.com/api.php?amount=10&type=multiple"
        val requestQueue = Volley.newRequestQueue(this)



        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val questionHandler = gson.fromJson(it,QuestionHandler::class.java)
                Log.d("apiFetch", "success")
                callback.invoke(questionHandler)
            },

            {
                Log.d("apiFetch", "error")
                Toast.makeText(
                    this, "Please check your Internet Connection", Toast.LENGTH_SHORT
                ).show()
            }

        )
        requestQueue.add(stringRequest)

    }



}


