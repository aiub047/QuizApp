package com.mak.quizapp

import android.app.AlertDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSubmit = findViewById<AppCompatButton>(R.id.btnSubmit)
        val btnReset = findViewById<AppCompatButton>(R.id.btnReset)

        btnSubmit.setOnClickListener {
            //showAlertDialog("Success", "Congratulations!")
            val radio1_2 = findViewById<RadioButton>(R.id.radio1_2)
            val radio2_1 = findViewById<RadioButton>(R.id.radio2_1)

            var score = 0
            if (radio1_2.isChecked) score++
            if (radio2_1.isChecked) score++


            if (score > 0) {
                val currentDateTime = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")
                val formattedDateTime = currentDateTime.format(formatter)
                showAlertDialog(
                    "Congratulations!",
                    "You submitted on $formattedDateTime\nYou achieved: ${score * 50}%"
                )
            } else {
                showAlertDialog(
                    "Oops!",
                    "Wrong answers.\nTry again..."
                )
            }
        }

        btnReset.setOnClickListener {
            val question1RadioGroup = findViewById<RadioGroup>(R.id.question1RadioGroup)
            val question2RadioGroup = findViewById<RadioGroup>(R.id.question2RadioGroup)

            uncheckAllRadioButtons(question1RadioGroup)
            uncheckAllRadioButtons(question2RadioGroup)
        }
    }

    private fun uncheckAllRadioButtons(radioGroup: RadioGroup?) {
        if (radioGroup != null) {
            for (i in 0 until radioGroup.childCount) {
                val radioButton = radioGroup.getChildAt(i) as? RadioButton
                radioButton?.isChecked = false
            }
        }
    }

    fun showAlertDialog(txtTitle: String?, txtBody: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("$txtTitle")
            .setMessage("$txtBody")

        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}