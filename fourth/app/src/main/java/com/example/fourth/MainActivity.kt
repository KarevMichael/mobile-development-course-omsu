package com.example.fourth

import android.app.Activity
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val forward = { title: String, description: String -> startActivity(Intent(this, LanguageInfo::class.java).apply {
        putExtra("TITLE", title)
        putExtra("DESCRIPTION", description)
    }) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.languages)
        listView.adapter = RowAdapter(this, forward)
    }
    private class RowAdapter(
        private val context: Context,
        private val forward: (String, String) -> Unit
    ): BaseAdapter() {
        private data class ProgrammingLanguage(val logo: Int, val name: String, val description: String = "lollololool")
        private val items = arrayOf(
            ProgrammingLanguage(R.drawable.ic_javascript_js_seeklogo_com, "JS"),
            ProgrammingLanguage(R.drawable.ic_kotlin_seeklogo_com, "Kotlin"),
            ProgrammingLanguage(R.drawable.ic_python_seeklogo_com, "Python"),
            ProgrammingLanguage(R.drawable.ic_java_seeklogo_com, "Java"),
            ProgrammingLanguage(R.drawable.ic_c_seeklogo_com, "C++")
        )
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            return (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.row, null).apply {
                val currentItem = items[position]
                findViewById<TextView>(R.id.language).text = currentItem.name
                findViewById<ImageView>(R.id.logo).setImageResource(currentItem.logo)
                setOnClickListener {
                    forward(currentItem.name, currentItem.description)
                }
            }
        }

        override fun getItem(position: Int) = items[position]

        override fun getItemId(position: Int) = position.toLong()

        override fun getCount() = items.size

    }
}