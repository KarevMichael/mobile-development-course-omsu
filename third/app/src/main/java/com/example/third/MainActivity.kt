package com.example.third

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var word = ""
    private val guesses = emptyArray<String>().toMutableList()
    private fun generateRandomWord() {
        word = Array(4) { Random.nextInt(0, 10) }.joinToString("")
    }
    private var win = false
    private fun mapWordToHint(currentWord: String): String {
        val numbersInWord = Array(word.length) { i -> word[i] }
        val numbersInCurrentWord = Array(currentWord.length) {  i -> currentWord[i] }
        val hint = numbersInCurrentWord.mapIndexed { i, num ->
            if (numbersInWord.contains(num)) (if(numbersInWord[i] == num) "В" else "К") else ""
        }.joinToString("")

        win = win || hint == "ВВВВ"

        return hint
    }
    private lateinit var listViewAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generateRandomWord()
        val input = findViewById<EditText>(R.id.input)
        val textView = findViewById<TextView>(R.id.text_view)
        val checkButton = findViewById<Button>(R.id.checkButton)
        val resetButton = findViewById<Button>(R.id.resetButton)
        listViewAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            emptyArray<String>().toMutableList()
        )
        findViewById<ListView>(R.id.list).apply { adapter = listViewAdapter }


        checkButton.setOnClickListener {
            if(!win) {
                guesses.add(input.text.toString())
                listViewAdapter.clear()
                listViewAdapter.addAll(guesses.mapIndexed { i, guess ->  "${i + 1}. $guess - ${mapWordToHint(guess)}"})
                if(win) {
                    textView.text = "You are won!"
                }
            }
        }

        resetButton.setOnClickListener {
            input.setText("")
            textView.text = ""
            listViewAdapter.clear()
            guesses.clear()
            win = false
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(input.windowToken, 0)
            generateRandomWord()
        }
    }
}