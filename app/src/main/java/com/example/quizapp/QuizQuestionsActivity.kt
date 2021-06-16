package com.example.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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
            if(binding.btnSubmit.text.toString().lowercase().equals("submit")){
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
                    binding.btnSubmit.text = "FINISH"
                }
                else{
                    binding.btnSubmit.text = "GO TO NEXT QUESTION"
                }
                return@setOnClickListener
            }
            else if(binding.btnSubmit.text.toString().lowercase().equals("go to next question")){

                currentPosition += 1
                setQuestion()
                setProgressBar()
                defaultOptionsView()
                binding.btnSubmit.text = "SUBMIT"
            }
            else{
                // finish

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

        val question = questionList?.get(currentPosition-1)?.question

        binding.tvQuestion.text = question

        val Quesoptions= mutableListOf<String?>()

        Quesoptions.add(questionList?.get(currentPosition-1)?.incorrect_answers?.get(0))
        Quesoptions.add(questionList?.get(currentPosition-1)?.incorrect_answers?.get(1))
        Quesoptions.add(questionList?.get(currentPosition-1)?.incorrect_answers?.get(2))
        Quesoptions.add(questionList?.get(currentPosition-1)?.correct_answer)

        Quesoptions.shuffle()

        binding.tvOptionOne.text = Quesoptions[0]
        binding.tvOptionTwo.text = Quesoptions[1]
        binding.tvOptionThree.text = Quesoptions[2]
        binding.tvOptionFour.text = Quesoptions[3]
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
        binding.tvProgress.text = "${currentPosition} / 10"
    }

}
