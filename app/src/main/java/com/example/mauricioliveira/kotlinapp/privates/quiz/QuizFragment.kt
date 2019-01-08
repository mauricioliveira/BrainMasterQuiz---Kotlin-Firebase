package com.example.mauricioliveira.kotlinapp.privates.quiz


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mauricioliveira.kotlinapp.BaseFragment
import com.example.mauricioliveira.kotlinapp.R
import com.example.mauricioliveira.kotlinapp.communication.model.Result
import com.example.mauricioliveira.kotlinapp.communication.model.ReturnData
import com.example.mauricioliveira.kotlinapp.privates.mainmenu.MainMenuFragment
import com.example.mauricioliveira.kotlinapp.utils.Util
import java.util.*


private const val RETURN_DATA_KEY = "return_data"

class QuizFragment : BaseFragment(), View.OnClickListener {

    private lateinit var returnData: ReturnData
    private lateinit var category: TextView
    private lateinit var question: TextView
    private lateinit var firstAnswer: Button
    private lateinit var secondAnswer: Button
    private lateinit var thirdAnswer: Button
    private lateinit var fourthAnswer: Button
    private var correctAnswer: String = String()
    private var answerNumber = 0
    private lateinit var listResult: List<Result>
    private lateinit var buttonList: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            returnData = it.getSerializable(RETURN_DATA_KEY) as ReturnData
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_quiz, container, false)

        this.category = rootView.findViewById(R.id.categoryTextView)
        this.question = rootView.findViewById(R.id.questionTextView)
        this.firstAnswer = rootView.findViewById(R.id.firstAnswerButton)
        this.secondAnswer = rootView.findViewById(R.id.secondAnswerButton)
        this.thirdAnswer = rootView.findViewById(R.id.thirdAnswerButton)
        this.fourthAnswer = rootView.findViewById(R.id.fourthAnswerButton)

        this.buttonList = listOf(this.firstAnswer,this.secondAnswer,this.thirdAnswer,this.fourthAnswer)

        this.firstAnswer.setOnClickListener(this)
        this.secondAnswer.setOnClickListener(this)
        this.thirdAnswer.setOnClickListener(this)
        this.fourthAnswer.setOnClickListener(this)

        this.listResult = returnData.results
        this.populateView(this.listResult[answerNumber])

        return rootView
    }


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
            Toast.makeText(mContext,"Resposta Correcta", Toast.LENGTH_LONG).show()
            answerNumber++
            if (answerNumber < this.listResult!!.size) {
                resetView()
                populateView(this.listResult!![answerNumber])
            } else {
                var fragment = MainMenuFragment.newInstance()

                this.fragmentManager!!.beginTransaction()
                        .replace(R.id.main_fl,fragment)
                        .commit()
            }
        } else {
            Toast.makeText(mContext,"Resposta Incorrecta", Toast.LENGTH_LONG).show()
        }
    }

    private fun populateView (result: Result) {
        var answerList = joinAnswers(Util.decode64toString(result.correctAnswer), Util.listDecode64toString(result.incorrectAnswers))
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

    private fun resetView() {
        for (i in buttonList.indices) {
            buttonList[i].visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        this.setToolbarTitle("Category Choice")
        this.setDisplayHome(false)
    }


    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(returnData: ReturnData) =
                QuizFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(RETURN_DATA_KEY,returnData)
                    }
                }
    }
}
