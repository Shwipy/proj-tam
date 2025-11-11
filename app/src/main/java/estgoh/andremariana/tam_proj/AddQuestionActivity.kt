package estgoh.andremariana.tam_proj

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_question)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tf_question = findViewById<EditText>(R.id.tf_question)

        var selected_answer_num = findViewById<RadioButton>(R.id.rb_two_questions)
        var selected_correct_answer = findViewById<RadioButton>(R.id.rb_one_correct)
        val group_num_answer = findViewById<RadioGroup>(R.id.group_num_answers)
        val group_correct_answer = findViewById<RadioGroup>(R.id.group_correct_answer)

        val tf_answer_one = findViewById<EditText>(R.id.tf_answer_one)
        val tf_answer_two = findViewById<EditText>(R.id.tf_answer_two)
        val tf_answer_three = findViewById<EditText>(R.id.tf_answer_three)
        val tf_answer_four = findViewById<EditText>(R.id.tf_answer_four)

        //https://stackoverflow.com/a/37362772
        group_num_answer.setOnCheckedChangeListener { group, checkedId ->
            selected_answer_num = findViewById<View?>(checkedId) as RadioButton

            val num_answers = selected_answer_num.text.toString().toInt()
            if(num_answers ==2 ){
                tf_answer_three.isEnabled = false
                tf_answer_four.isEnabled = false
                tf_answer_three.setText("")
                tf_answer_four.setText("")

            }
            if (num_answers >= 3){
                tf_answer_three.isEnabled = true
                tf_answer_four.isEnabled = false
                tf_answer_four.setText("")
            }
            if (num_answers == 4){
                tf_answer_four.isEnabled = true
            }


        }

        group_correct_answer.setOnCheckedChangeListener { group, checkedId ->
            selected_correct_answer = findViewById<View?>(checkedId) as RadioButton
        }

        var btn_cancel = findViewById<Button>(R.id.btn_cancel_question)
        btn_cancel.setOnClickListener {
            this.finish()
        }

        var btn_add = findViewById<Button>(R.id.btn_add_question)
        btn_add.setOnClickListener {

            val question = tf_question.text.toString()

            val answer_one = tf_answer_one.text.toString()
            val answer_two = tf_answer_two.text.toString()
            val answer_three = tf_answer_three.text.toString()
            val answer_four = tf_answer_four.text.toString()

            val num_answers = selected_answer_num.text.toString().toInt()
            val correct_answer = selected_correct_answer.text.toString().toInt()

            if(!question.trim().isEmpty()){
                val intent = Intent()
                intent.putExtra("question",question)

                if (correct_answer > num_answers){
                    Toast.makeText(this,"Resposta Correta Inválida", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener

                } else if (num_answers == 2 && !answer_one.trim().isEmpty() && !answer_two.trim().isEmpty()){

                    intent.putExtra("answer1",answer_one)
                    intent.putExtra("answer2",answer_two)
                    intent.putExtra("correct",correct_answer)

                    this.setResult(Activity.RESULT_OK,intent)
                    this.finish()

                }else if (num_answers == 3 && !answer_one.trim().isEmpty() && !answer_two.trim().isEmpty() && !answer_three.trim().isEmpty()){

                    intent.putExtra("answer1",answer_one)
                    intent.putExtra("answer2",answer_two)
                    intent.putExtra("answer3",answer_three)
                    intent.putExtra("correct",correct_answer)

                    this.setResult(Activity.RESULT_OK,intent)
                    this.finish()

                }else if (num_answers == 4 && !answer_one.trim().isEmpty() && !answer_two.trim().isEmpty() && !answer_three.trim().isEmpty() && !answer_four.trim().isEmpty()){

                    intent.putExtra("answer1",answer_one)
                    intent.putExtra("answer2",answer_two)
                    intent.putExtra("answer3",answer_three)
                    intent.putExtra("answer4",answer_four)
                    intent.putExtra("correct",correct_answer)

                    this.setResult(Activity.RESULT_OK,intent)
                    this.finish()

                }else{
                    Toast.makeText(this,"Resposta(s) não Introduzidas", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            } else{
                Toast.makeText(this,"Questão não foi Introduzida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
    }
}