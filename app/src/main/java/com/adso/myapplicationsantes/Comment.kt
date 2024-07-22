package com.adso.myapplicationsantes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Comment : AppCompatActivity() {

    private lateinit var  backToPostIcon : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        backToPostIcon = findViewById(R.id.backToPostIcon)



        val commentEditText: EditText = findViewById(R.id.commentEditText)
        val postCommentButton: Button = findViewById(R.id.postCommentButton)
        val commentsTextView: TextView = findViewById(R.id.commentsTextView)


        postCommentButton.setOnClickListener {
            val comment = commentEditText.text.toString()
            if (comment.isNotEmpty()) {
                commentsTextView.append("\n$comment")
                commentEditText.text.clear()
            }
        }

        backToPostIcon.setOnClickListener {
            val intent = Intent(this@Comment, Home2Fragment::class.java)
            startActivity(intent)
        }
    }
}