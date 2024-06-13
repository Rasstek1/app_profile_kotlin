package com.racine.app_profile_kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.racine.app_profile_kotlin.adapter.ProfileAdapter
import com.racine.app_profile_kotlin.databinding.ActivityMainBinding//xzcxzcxzcxz

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var profileAdapter: ProfileAdapter
    private val profiles = mutableListOf<Profile>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileAdapter = ProfileAdapter()
        binding.recyclerViewProfiles.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewProfiles.adapter = profileAdapter

        binding.buttonAddProfile.setOnClickListener {
            val intent = Intent(this, AddEditProfileActivity::class.java)
            startActivityForResult(intent, ADD_PROFILE_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PROFILE_REQUEST && resultCode == Activity.RESULT_OK) {
            val profile = data?.getParcelableExtra<Profile>("PROFILE")
            profile?.let {
                profiles.add(it)
                profileAdapter.submitList(profiles.toList())
            }
        }
    }

    companion object {
        const val ADD_PROFILE_REQUEST = 1
    }
}
