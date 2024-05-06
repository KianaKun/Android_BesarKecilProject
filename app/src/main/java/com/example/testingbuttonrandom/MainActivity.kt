package com.example.testingbuttonrandom

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var leftRandomButton: Button
    private lateinit var rightRandomButton: Button
    private lateinit var startButton: Button
    private lateinit var scoreTextView: TextView

    private var leftNumber = 0
    private var rightNumber = 0
    private var score = 0

    private val maximal = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout) // Set the layout

        // Initialize views
        leftRandomButton = findViewById(R.id.leftButton)
        rightRandomButton = findViewById(R.id.rightButton)
        startButton = findViewById(R.id.startButton)
        scoreTextView = findViewById(R.id.scoreTextView) // Update ID here

        // Set click listeners
        startButton.setOnClickListener {
            startNewGame()
        }

        leftRandomButton.setOnClickListener {
            checkAnswer(leftNumber, rightNumber)
        }

        rightRandomButton.setOnClickListener {
            checkAnswer(rightNumber, leftNumber)
        }

        startNewGame() // Start the game when the activity is created
    }

    private fun startNewGame() {
        score = 0
        generateNewNumbers()
        updateButtonTexts()
        updateScoreText()
    }

    private fun generateNewNumbers() {
        leftNumber = (0..50).random()
        rightNumber = (0..50).random()
    }

    private fun checkAnswer(selectedNumber: Int, otherNumber: Int) {
        if (score >= maximal) {
            score = 0 // Reset score if already played more than 10 times
        } else {
            if (selectedNumber > otherNumber) {
                score++
            }
        }
        generateNewNumbers()
        updateButtonTexts()
        updateScoreText()
    }

    private fun updateButtonTexts() {
        leftRandomButton.text = leftNumber.toString()
        rightRandomButton.text = rightNumber.toString()
    }

    private fun updateScoreText() {
        scoreTextView.text = getString(R.string.score_text, score)
    }
}
