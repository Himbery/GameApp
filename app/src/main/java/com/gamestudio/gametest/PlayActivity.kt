package com.gamestudio.gametest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.play_activity.*
import java.util.*

class PlayActivity : AppCompatActivity() {

    private var kill = 0
    private var secretWord = ""
    private var secretDisplay = ""
    private val correctGuesses = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play_activity)

        val extras = intent.extras
        if (extras != null) {
            @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
            secretWord = extras.getString("secretWord")
        }

        newGame()
    }

    fun guessTry(click: View) {
        if (click === tryButton) {
            val pGuess = playerGuess.text.toString().toLowerCase()
            playerGuess.text = null

            // Player asks for a letter
            if (pGuess.length == 1) {
                if (pGuess in secretWord.toLowerCase()) {

                    correctGuesses.add(pGuess)
                    refactorSecret()

                    Toast.makeText(applicationContext,"Good guess!",Toast.LENGTH_SHORT).show()
                    checkWin()
                    return
                }
            }

            // Player tries to guess
            if (pGuess.length > 1) {
                if (pGuess.toLowerCase() == secretWord.toLowerCase()) {
                    showDialog(true)
                    return
                }
            }

            // Player fails
            kill++
            when (kill) {
                1 -> hangmanDrawing.setImageResource(R.drawable.hang1)
                2 -> hangmanDrawing.setImageResource(R.drawable.hang2)
                3 -> hangmanDrawing.setImageResource(R.drawable.hang3)
                4 -> hangmanDrawing.setImageResource(R.drawable.hang4)
                5 -> hangmanDrawing.setImageResource(R.drawable.hang5)
                6 -> {
                    hangmanDrawing.setImageResource(R.drawable.hang6)
                    showDialog(false)
                }
            }
        }
    }

    // Recreate display of the secret word based on progress
    private fun refactorSecret() {
        secretDisplay = ""
        secretWord.forEach {
                s -> secretDisplay += (checkIfGuessed(s.toString()))
        }
        toBeGuessed.text = secretDisplay
    }

    // Reveal correctly guessed letters
    private fun checkIfGuessed(s: String) : String {
        return when (correctGuesses.contains(s.toLowerCase())) {
            true -> s
            false -> "_"
        }
    }

    // If a char from secretWord isn't in guess chars then player didn't guess everything yet
    private fun checkWin() {
        var everythingGuessed = true
        secretWord.toLowerCase().forEach { c ->
            if (!correctGuesses.contains(c.toString()))
                everythingGuessed = false
        }
        if(everythingGuessed)
            showDialog(true)
    }

    // Win/Lose alert
    private fun showDialog(won: Boolean) {
        val builder = AlertDialog.Builder(this@PlayActivity)
        if(won) {
            builder.setTitle(getString(R.string.congrat))
        }else{
            builder.setTitle(getString(R.string.fin))
        }
        builder.setMessage(getString(R.string.next))

        builder.setPositiveButton(getString(R.string.start)){ _, _ ->

            // This one below is in main view more readable
            secretWord = resources.getStringArray(R.array.guessWords)[Random().nextInt(resources.getStringArray(R.array.guessWords).size-0)+0]
            newGame()

            Toast.makeText(applicationContext,getString(R.string.new_game),Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton(getString(R.string.no)){ _, _ ->
            finish()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // Reset & Create New Game
    private fun newGame(){
        hangmanDrawing.setImageResource(R.drawable.hang0)
        kill = 0
        secretDisplay = ""
        correctGuesses.clear()

        repeat(secretWord.length) {
            secretDisplay += "_"
        }

        toBeGuessed.text = secretDisplay
    }

}