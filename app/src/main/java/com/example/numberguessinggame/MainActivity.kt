package com.example.numberguessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var editText: EditText
    lateinit var imageButtonCheck: ImageButton
    lateinit var newGameButton: Button

    var random: Int = nextInt(1,1000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        imageButtonCheck = findViewById(R.id.imageButtonCheck)
        newGameButton = findViewById(R.id.newGameButton)

        textView.text = "Please enter your guess:"

        var timeClicked = 0

        imageButtonCheck.setOnClickListener {

            timeClicked += 1

            val number: Int = editText.text.toString().toInt()

            if (number < random) {

                textView.text = "Hint: It's higher!"
                editText.text.clear()

            } else if (number > random) {

                textView.text = "Hint: It's lower!"
                editText.text.clear()

            } else {

                textView.text = "Collect! count: " + timeClicked.toString()

            }
        }

        newGameButton.setOnClickListener{

            reset()

        }

    }

    fun reset() {
        random = nextInt(1,1000)
        textView.text = "Please enter your guess:"
        editText.text.clear()
    }
}