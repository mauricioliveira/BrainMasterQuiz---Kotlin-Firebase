package com.example.mauricioliveira.kotlinapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mauricioliveira.kotlinapp.communication.interfaces.GetAmountTaskListener
import com.example.mauricioliveira.kotlinapp.communication.model.Result
import com.example.mauricioliveira.kotlinapp.communication.model.ReturnData
import com.example.mauricioliveira.kotlinapp.communication.task.GetAmountTask
import com.example.mauricioliveira.kotlinapp.utils.Util
import java.util.*

class MainActivityBCK : BaseActivity(), GetAmountTaskListener, View.OnClickListener {

    private lateinit var category: TextView
    private lateinit var question: TextView
    private lateinit var firstAnswer: Button
    private lateinit var secondAnswer: Button
    private lateinit var thirdAnswer: Button
    private lateinit var fourthAnswer: Button
    private var correctAnswer: String = String()
    private var answerNumber = 0
    private var listResult: List<Result> = listOf()
    private lateinit var buttonList: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bck)

        this.category = findViewById(R.id.categoryTextView)
        this.question = findViewById(R.id.questionTextView)
        this.firstAnswer = findViewById(R.id.firstAnswerButton)
        this.secondAnswer = findViewById(R.id.secondAnswerButton)
        this.thirdAnswer = findViewById(R.id.thirdAnswerButton)
        this.fourthAnswer = findViewById(R.id.fourthAnswerButton)

        this.buttonList = listOf(this.firstAnswer,this.secondAnswer,this.thirdAnswer,this.fourthAnswer)

        this.firstAnswer.setOnClickListener(this)
        this.secondAnswer.setOnClickListener(this)
        this.thirdAnswer.setOnClickListener(this)
        this.fourthAnswer.setOnClickListener(this)

        this.getData()
    }

    fun getData () {
        var amountTask = GetAmountTask(this)
        amountTask.getAmount("","")
    }


    override fun onGetAmountTaskSuccess(returnData: ReturnData) {
        // T ODO("not implemented")
        // To change body of created functions use File | Settings | File Templates.
        this.listResult = returnData.results
        //this.populateView(returnData.results[answerNumber])
        this.populateView(this.listResult[answerNumber])
    }

    override fun onGetAmountTaskUnSucess(error: Throwable) {
        // T ODO("not implemented")
        //To change body of created functions use File | Settings | File Templates.
        Log.d("teste","teste")
    }

    private fun populateView (result: Result) {
        var answerList = joinAnswers(Util.decode64toString(result.correctAnswer),Util.listDecode64toString(result.incorrectAnswers))
        var buttonControl = 0
        Collections.shuffle(answerList)
        this.correctAnswer = Util.decode64toString(result.correctAnswer)
        this.category.text = Util.decode64toString(result.category)
        this.question.text = Util.decode64toString(result.question)

        for (i in answerList.indices) {
            buttonList[i].text = answerList[i]
            buttonControl++
        }

        while (buttonControl < buttonList.size) {
            buttonList[buttonControl].visibility = View.GONE
            buttonControl++
        }
    }

    private fun joinAnswers (correctAnswer: String, list: List<String>) : List<String> = list.plus(correctAnswer)

    override fun onClick(p0: View?) {
        when (p0) {
            firstAnswer -> verifyAnswer(firstAnswer)
            secondAnswer -> verifyAnswer(secondAnswer)
            thirdAnswer -> verifyAnswer(thirdAnswer)
            fourthAnswer -> verifyAnswer(fourthAnswer)
        }
    }

    fun verifyAnswer (button: Button) {
        if (button.text == this.correctAnswer) {
            Toast.makeText(this,"Resposta Correcta",Toast.LENGTH_LONG).show()
            answerNumber++
            if (answerNumber < this.listResult!!.size) {
                resetView()
                populateView(this.listResult!![answerNumber])
            }
        } else {
            Toast.makeText(this,"Resposta Incorrecta",Toast.LENGTH_LONG).show()
        }
    }

    private fun resetView() {
        for (i in buttonList.indices) {
            buttonList[i].visibility = View.VISIBLE
        }
    }
}
