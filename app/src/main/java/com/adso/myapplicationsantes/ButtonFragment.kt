package com.adso.myapplicationsantes

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.adso.myapplicationsantes.databinding.ActivityButtonFragmentBinding
import java.util.Calendar

class ButtonFragment : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private lateinit var birthdateEditText: EditText
    private val PICK_IMAGE = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_fragment)

        profileImageView = findViewById(R.id.profile_image)
        val selectImageButton: Button = findViewById(R.id.select_image_button)
        birthdateEditText = findViewById(R.id.update_birthdate)
        val saveButton: Button = findViewById(R.id.save_button)

        selectImageButton.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, PICK_IMAGE)
        }
        birthdateEditText.setOnClickListener {
            showDatePickerDialog()
        }

        saveButton.setOnClickListener {

            saveProfileChanges()
        }

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data?.data
            profileImageView.setImageURI(imageUri)
        } else {
            Toast.makeText(this, "No seleccionaste ninguna imagen", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            birthdateEditText.setText(formattedDate)
        }, year, month, day)
        datePickerDialog.show()
    }
    private fun saveProfileChanges() {

        Toast.makeText(this, "Perfil actualizado", Toast.LENGTH_SHORT).show()

        finish()
    }
}