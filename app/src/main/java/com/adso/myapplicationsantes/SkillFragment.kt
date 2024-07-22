package com.adso.myapplicationsantes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SkillFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_skill_fragment)

        val btnLogin = findViewById<Button>(R.id.btn_login)
        val option1: TextView = findViewById(R.id.option1)
        val option2: TextView = findViewById(R.id.option2)
        val option3: TextView = findViewById(R.id.option3)

        btnLogin.setOnClickListener {
            val intent = Intent(this, ButtonFragment::class.java)
            startActivity(intent)
        }

        option1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        option2.setOnClickListener {
            val intent = Intent(this, ReferenceActivity::class.java)
            startActivity(intent)
        }

        option3.setOnClickListener {
            val intent = Intent(this, SkillFragment::class.java)
            startActivity(intent)
        }

    }
}



