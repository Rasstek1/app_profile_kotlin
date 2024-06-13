package com.racine.app_profile_kotlin.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.racine.app_profile_kotlin.Profile
import com.racine.app_profile_kotlin.R

class ProfileAdapter : ListAdapter<Profile, ProfileAdapter.ProfileViewHolder>(ProfileDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false)
        return ProfileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = getItem(position)
        holder.bind(profile)
    }

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        private val birthYearTextView: TextView = itemView.findViewById(R.id.textViewBirthYear)
        private val cityTextView: TextView = itemView.findViewById(R.id.textViewCity)
        private val profileImageView: ImageView = itemView.findViewById(R.id.imageViewProfile)

        fun bind(profile: Profile) {
            nameTextView.text = profile.name
            birthYearTextView.text = profile.birthYear.toString()
            cityTextView.text = profile.city

            val photoUri = profile.photoUri
            if (photoUri.isNotEmpty()) {
                profileImageView.setImageURI(Uri.parse(photoUri))
            } else {
                profileImageView.setImageResource(R.drawable.default_profile_photo)
            }
        }
    }

    class ProfileDiffCallback : DiffUtil.ItemCallback<Profile>() {
        override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem == newItem
        }
    }
}
