package com.racine.app_profile_kotlin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.racine.app_profile_kotlin.databinding.ActivityAddEditProfileBinding

class AddEditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditProfileBinding
    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val birthYear = binding.editTextBirthYear.text.toString().toIntOrNull()
            val city = binding.editTextCity.text.toString()
            val photoUri = selectedImageUri?.toString() ?: ""

            if (name.isEmpty() || birthYear == null || city.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val profile = Profile(name, birthYear, city, photoUri)
            val resultIntent = Intent().apply {
                putExtra("PROFILE", profile)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        binding.buttonChoosePhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            binding.imageViewProfile.setImageURI(selectedImageUri)
        }
    }
}
