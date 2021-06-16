package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity() {


    private var questionList : List<Result>? = null
    private var currentPosition = 1
    private var score: Int = 0
    private lateinit var binding:ActivityQuizQuestionsBinding
    private var selectedOption = 0
    private lateinit var options : List<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        options = listOf(
            binding.tvOptionOne,
            binding.tvOptionTwo,
            binding.tvOptionThree,
            binding.tvOptionFour
        )
        val view = binding.root

        setContentView(view)

        val questionHandler = intent.getSerializableExtra("EXTRA_QuestionHandler") as QuestionHandler

        val name = intent.getStringExtra("EXTRA_NAME")
        //List of questions
        questionList = questionHandler.results



        setQuestion()




        // event listeners to options
        binding.tvOptionOne.setOnClickListener {
            defaultOptionsView()
            selectedOption = 1
            setSelectedOptionView(1)
        }

        binding.tvOptionTwo.setOnClickListener {
            defaultOptionsView()
            selectedOption = 2
            setSelectedOptionView(2)
        }

        binding.tvOptionThree.setOnClickListener {
            defaultOptionsView()
            selectedOption = 3
            setSelectedOptionView(3)
        }

        binding.tvOptionFour.setOnClickListener {
            defaultOptionsView()
            selectedOption = 4
            setSelectedOptionView(4)
        }







        // submit button
        binding.btnSubmit.setOnClickListener {
            if(selectedOption == 0){
                val toast = Toast.makeText(this,"Please select an option !",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER,0,0)
                toast.show()
                return@setOnClickListener

            }
            if(binding.btnSubmit.text.toString().lowercase() == "submit"){
                setCorrectColor()
                if(options[0].text.equals(questionList?.get(currentPosition-1)?.correct_answer) && selectedOption == 1){
                    score += 1
                }
                if(options[1].text.equals(questionList?.get(currentPosition-1)?.correct_answer) && selectedOption == 2){
                    score += 1
                }
                if(options[2].text.equals(questionList?.get(currentPosition-1)?.correct_answer) && selectedOption == 3){
                    score += 1
                }
                if(options[3].text.equals(questionList?.get(currentPosition-1)?.correct_answer) && selectedOption == 4){
                    score += 1
                }


                if(currentPosition == 10){
                    binding.btnSubmit.text = getString(R.string.finish)
                }
                else{
                    binding.btnSubmit.text = getString(R.string.go_to_next_question)
                }
                return@setOnClickListener
            }
            else if(binding.btnSubmit.text.toString().lowercase() == "go to next question"){

                currentPosition += 1
                setQuestion()
                setProgressBar()
                defaultOptionsView()
                binding.btnSubmit.text = getString(R.string.submit)
            }
            else{
                // finish
                Intent(this,FinishActivity::class.java).also{
                    it.putExtra("EXTRA_SCORE",score.toString())
                    it.putExtra("EXTRA_NAME",name)
                    startActivity(it)
                }
                finish()
            }


        }




    }



    private fun defaultOptionsView(){


        if(!this::binding.isInitialized){
            return
        }

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.option_border
            )
        }
    }


    private fun setSelectedOptionView(id: Int){


        if(!this::binding.isInitialized){
            return
        }

        when(id){
            1 -> {binding.tvOptionOne.background = ContextCompat.getDrawable(
                this,
                R.drawable.selected_option_border
            )}

            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.selected_option_border
                )
            }

            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.selected_option_border
                )
            }

            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.selected_option_border
                )
            }
        }
    }



    private fun setQuestion(){

        if(!this::binding.isInitialized){
            return
        }

        val question = stringFilter(questionList?.get(currentPosition-1)?.question)

        binding.tvQuestion.text = question

        val quesoptions= mutableListOf<String?>()

        quesoptions.add(stringFilter(questionList?.get(currentPosition-1)?.incorrect_answers?.get(0)))
        quesoptions.add(stringFilter(questionList?.get(currentPosition-1)?.incorrect_answers?.get(1)))
        quesoptions.add(stringFilter(questionList?.get(currentPosition-1)?.incorrect_answers?.get(2)))
        quesoptions.add(stringFilter(questionList?.get(currentPosition-1)?.correct_answer))

        quesoptions.shuffle()

        binding.tvOptionOne.text = quesoptions[0]
        binding.tvOptionTwo.text = quesoptions[1]
        binding.tvOptionThree.text = quesoptions[2]
        binding.tvOptionFour.text = quesoptions[3]
    }


    private fun setCorrectColor(){

        if(!this::binding.isInitialized){
            return
        }

        if(options[0].text.equals(questionList?.get(currentPosition-1)?.correct_answer))
        {

           options[0].setTextColor(Color.parseColor("#000000"))
           options[0].background = ContextCompat.getDrawable(
               this,
               R.drawable.correct_answer
           )
        }
        if(options[1].text.equals(questionList?.get(currentPosition-1)?.correct_answer))
        {
            options[1].setTextColor(Color.parseColor("#000000"))
            options[1].background = ContextCompat.getDrawable(
                this,
                R.drawable.correct_answer
            )
        }
        if(options[2].text.equals(questionList?.get(currentPosition-1)?.correct_answer))
        {
            options[2].setTextColor(Color.parseColor("#000000"))
            options[2].background = ContextCompat.getDrawable(
                this,
                R.drawable.correct_answer
            )
        }
        if(options[3].text.equals(questionList?.get(currentPosition-1)?.correct_answer))
        {
            options[3].setTextColor(Color.parseColor("#000000"))
            options[3].background = ContextCompat.getDrawable(
                this,
                R.drawable.correct_answer
            )
        }

    }


    private fun setProgressBar(){

        // setting progress bar progress
        binding.pbProgress.progress = currentPosition
        binding.tvProgress.text = "$currentPosition"+"/ 10"
    }


    private fun stringFilter(string: String?):String?{

        var str = string

        str = str?.replace("&nbsp;","")
        str = str?.replace("&quot;","\"")
        str = str?.replace("&lt;","<")
        str = str?.replace("&amp;","&")
        str = str?.replace("&apos;","\'")
        str = str?.replace("&gt;",">")

        str = str?.replace("&#160;","")
        str = str?.replace("&#034;","\"")
        str = str?.replace("&#060;","<")
        str = str?.replace("&#038;","&")
        str = str?.replace("&#039;","\'")
        str = str?.replace("&#062;",">")


        return str
    }

}
