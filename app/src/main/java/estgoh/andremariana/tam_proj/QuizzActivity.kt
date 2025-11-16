package estgoh.andremariana.tam_proj

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class QuizzActivity : AppCompatActivity() {
    private fun disableButtons(){
        val btn_op_1 = findViewById<Button>(R.id.btn_option_1)
        val btn_op_2 = findViewById<Button>(R.id.btn_option_2)
        val btn_op_3 = findViewById<Button>(R.id.btn_option_3)
        val btn_op_4 = findViewById<Button>(R.id.btn_option_4)

        btn_op_1.isEnabled = false
        btn_op_2.isEnabled = false
        btn_op_3.isEnabled = false
        btn_op_4.isEnabled = false

    }

    private fun showCorrect(correct:Int){
        val btn_op_1 = findViewById<Button>(R.id.btn_option_1)
        val btn_op_2 = findViewById<Button>(R.id.btn_option_2)
        val btn_op_3 = findViewById<Button>(R.id.btn_option_3)
        val btn_op_4 = findViewById<Button>(R.id.btn_option_4)

        val correct_color = ContextCompat.getColor(this, R.color.correct)
        val new_text_color = ContextCompat.getColor(this, R.color.white)

        val right_answer_button = when(correct){
            1 -> btn_op_1
            2 -> btn_op_2
            3 -> btn_op_3
            else -> btn_op_4
        }
        right_answer_button.backgroundTintList = ColorStateList.valueOf(correct_color)
        right_answer_button.setTextColor(ColorStateList.valueOf(new_text_color))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quizz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tv_question = findViewById<TextView>(R.id.tv_question)
        val btn_op_1 = findViewById<Button>(R.id.btn_option_1)
        val btn_op_2 = findViewById<Button>(R.id.btn_option_2)
        val btn_op_3 = findViewById<Button>(R.id.btn_option_3)
        val btn_op_4 = findViewById<Button>(R.id.btn_option_4)

        val question = intent.extras?.getString("question")!!
        val correct_answer = intent.extras?.getInt("correct_answer")!!

        val answer_one = intent.extras?.getString("answers_one")!!
        val answer_two = intent.extras?.getString("answers_two")!!
        val answer_three = intent.extras?.getString("answers_three")!!
        val answer_four = intent.extras?.getString("answers_four")!!

        val correct_color = ContextCompat.getColor(this, R.color.correct)
        val wrong_color = ContextCompat.getColor(this, R.color.wrong)
        val new_text_color = ContextCompat.getColor(this, R.color.white)

        if (answer_three == "null"){
            //https://www.geeksforgeeks.org/kotlin/how-to-make-a-button-invisible-in-android
            btn_op_3.visibility = View.GONE
        }
        if (answer_four == "null"){
            btn_op_4.visibility = View.GONE
        }

        tv_question.text = question
        btn_op_1.text = answer_one
        btn_op_2.text = answer_two
        btn_op_3.text = answer_three
        btn_op_4.text = answer_four

        btn_op_1.setOnClickListener {
            if (correct_answer == 1){
//              https://www.geeksforgeeks.org/android/how-to-change-the-background-color-of-button-in-android-using-colorstatelist
                btn_op_1.backgroundTintList = ColorStateList.valueOf(correct_color)
                btn_op_1.setTextColor(ColorStateList.valueOf(new_text_color))
            }else{
                btn_op_1.backgroundTintList = ColorStateList.valueOf(wrong_color)
                btn_op_1.setTextColor(ColorStateList.valueOf(new_text_color))
                showCorrect(correct_answer)
            }
            disableButtons()
        }

        btn_op_2.setOnClickListener {
            if (correct_answer == 2){
                btn_op_2.backgroundTintList = ColorStateList.valueOf(correct_color)
                btn_op_2.setTextColor(ColorStateList.valueOf(new_text_color))
            }else{
                btn_op_2.backgroundTintList = ColorStateList.valueOf(wrong_color)
                btn_op_2.setTextColor(ColorStateList.valueOf(new_text_color))
                showCorrect(correct_answer)
            }
            disableButtons()

        }

        btn_op_3.setOnClickListener {
            if (correct_answer == 3){
                btn_op_3.backgroundTintList = ColorStateList.valueOf(correct_color)
                btn_op_3.setTextColor(ColorStateList.valueOf(new_text_color))
            }else{
                btn_op_3.backgroundTintList = ColorStateList.valueOf(wrong_color)
                btn_op_3.setTextColor(ColorStateList.valueOf(new_text_color))
                showCorrect(correct_answer)
            }
            disableButtons()

        }

        btn_op_4.setOnClickListener {
            if (correct_answer == 4){
                btn_op_4.backgroundTintList = ColorStateList.valueOf(correct_color)
                btn_op_4.setTextColor(ColorStateList.valueOf(new_text_color))
            }else{
                btn_op_4.backgroundTintList = ColorStateList.valueOf(wrong_color)
                btn_op_4.setTextColor(ColorStateList.valueOf(new_text_color))
                showCorrect(correct_answer)
            }
            disableButtons()

        }



        val btn_leave_quizz = findViewById<ImageButton>(R.id.btn_leave_quizz)
        btn_leave_quizz.setOnClickListener{
            this.finish()
        }
    }
}