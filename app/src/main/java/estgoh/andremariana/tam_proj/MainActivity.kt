package estgoh.andremariana.tam_proj

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val quizs = ArrayList<Quiz>()
    val questions = ArrayList<Question>()

    private val getQuizz = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        Log.d("Result",result.toString())
        if (result.resultCode == Activity.RESULT_OK) {
            val intentData = result.data

            val title = intentData?.getStringExtra("title")!!
            val description = intentData.getStringExtra("description")!!
            val time = intentData.getIntExtra("time",0)
//            Log.d("Title",title)
//            Log.d("Description",description)
//            Log.d("Timer", time.toString())

            quizs.add(Quiz(title, description,time))
            findViewById<TextView>(R.id.quiz_number).setText(quizs.size.toString())
        }
    }

    private val getQuestion = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        Log.d("Result",result.toString())
        if (result.resultCode == Activity.RESULT_OK) {
            val intentData = result.data

            val question = intentData?.getStringExtra("question")!!
            val answer_one = intentData.getStringExtra("answer1")!!
            val answer_two = intentData.getStringExtra("answer2")!!
            val answer_three = intentData.getStringExtra("answer3")
            val answer_four = intentData.getStringExtra("answer4")

            val correct_answer = intentData.getIntExtra("correct",1)

            questions.add(Question(question,correct_answer,listOf(answer_one,answer_two,answer_three,answer_four)))
            findViewById<TextView>(R.id.question_number).setText(questions.size.toString())
//            Log.d("Question",question)
//            Log.d("Answer1",answer_one)
//            Log.d("Answer2",answer_two)
//            Log.d("Answer3",answer_three.toString())
//            Log.d("Answer4",answer_four.toString())
//            Log.d("Correct", correct_answer.toString())

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var btn_add_quiz = findViewById<Button>(R.id.btn_menu_add_quizz)
        btn_add_quiz.setOnClickListener{
            val intent = Intent(this, AddQuizActivity::class.java)
            getQuizz.launch(intent)
        }

        var btn_add_question = findViewById<Button>(R.id.btn_menu_add_quest)
        btn_add_question.setOnClickListener{
            val intent = Intent(this, AddQuestionActivity::class.java)
            getQuestion.launch(intent)
        }

        var btn_solve_question = findViewById<Button>(R.id.btn_menu_solve)
        btn_solve_question.setOnClickListener{
            val intent = Intent(this, QuizActivity::class.java)
            this.startActivity(intent)
        }
    }
}