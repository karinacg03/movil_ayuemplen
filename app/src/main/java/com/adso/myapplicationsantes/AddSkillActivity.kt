package com.adso.myapplicationsantes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddSkillActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_skill)
        val titleEditText: EditText = findViewById(R.id.title_edit_text)
        val descriptionEditText: EditText = findViewById(R.id.description_edit_text)
        val saveButton: Button = findViewById(R.id.save_button)

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("skill_title", title)
            resultIntent.putExtra("skill_description", description)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}