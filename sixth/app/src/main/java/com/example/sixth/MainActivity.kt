package com.example.sixth

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.widget.doOnTextChanged
import com.scaledrone.lib.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), RoomListener {

    private val channelId = ""
    private val roomName = "omsu_course"
    private lateinit var scaledrone: Scaledrone
    private lateinit var messageField: TextView
    private lateinit var codeInput: EditText
    private lateinit var messageInput: EditText

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getEncodedString(): String {
        val messageChars = messageInput.text.chars().toArray()
        val encodeChars = codeInput.text.chars().toArray()

        return messageChars.mapIndexed { i, char -> char xor encodeChars[i] }.map { it.toChar() }.joinToString("")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getDecodedString(message: String): String {
        val messageChars = message.chars().toArray()
        val encodeChars = codeInput.text.chars().toArray()

        return messageChars.mapIndexed { i, char -> char xor encodeChars[i] }.map { it.toChar() }.joinToString("")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendButton = findViewById<Button>(R.id.send).apply { isEnabled = false }

        messageInput = findViewById(R.id.messageInput)
        messageInput.doOnTextChanged { text, _, _, _ -> run {
            sendButton.isEnabled = text?.length ?: 0 != 0 && text?.length ?: 0 == codeInput.text.length
        } }

        sendButton.setOnClickListener { scaledrone.publish(roomName, getEncodedString()) }

        messageField = findViewById(R.id.message)
        codeInput = findViewById(R.id.codeInput)
        codeInput.doOnTextChanged { text, _, _, _ -> run {
            sendButton.isEnabled = messageInput.text.length == text?.length ?: 0 && messageInput.text.isNotEmpty()
        } }
        scaledrone = Scaledrone(channelId)
        scaledrone.connect(object: Listener {
            override fun onOpen() {
                scaledrone.subscribe(roomName, this@MainActivity)
            }

            override fun onFailure(ex: Exception?) {}

            override fun onOpenFailure(ex: Exception?) {}

            override fun onClosed(reason: String?) {}

        })
    }

    override fun onOpen(room: Room?) {}

    override fun onOpenFailure(room: Room?, ex: Exception?) {}

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onMessage(room: Room?, message: Message?) {
        messageField.text = getDecodedString(message?.data?.asText().toString())
    }
}