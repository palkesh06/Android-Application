package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BasicsAdapter(private val basics: Basics) : RecyclerView.Adapter<BasicsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_basics, parent, false)
        return BasicsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BasicsViewHolder, position: Int) {
        holder.bind(basics)
    }

    override fun getItemCount(): Int {
        return 1
    }
}


class BasicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    private val labelTextView: TextView = itemView.findViewById(R.id.labelTextView)
    private val profileImageView: ImageView = itemView.findViewById(R.id.profileImageView)
    private val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)
    private val phoneTextView: TextView = itemView.findViewById(R.id.phoneTextView)
    private val websiteTextView: TextView = itemView.findViewById(R.id.websiteTextView)
    private val summaryTextView: TextView = itemView.findViewById(R.id.summaryTextView)
    private val locationTextView: TextView = itemView.findViewById(R.id.locationTextView)
    private val profilesContainer: LinearLayout = itemView.findViewById(R.id.profilesContainer)


    fun bind(basics: Basics) {
        nameTextView.text = "Name: ${basics.name}"
        labelTextView.text = "Label: ${basics.label}"
        emailTextView.text = "Email: ${basics.email}"
        phoneTextView.text = "Phone: ${basics.phone}"
        websiteTextView.text = "Website: ${basics.website}"
        summaryTextView.text = "Summary:\n      ${basics.summary}"
        locationTextView.text = "Location: ${basics.location} \n\nProfiles:"
        // Load profile image using Picasso
        val imageUrl = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?cs=srgb&dl=pexels-pixabay-220453.jpg&fm=jpg"
        Picasso.get().load(imageUrl).into(profileImageView)
        //Load social profiles
        val inflater = LayoutInflater.from(itemView.context)
        for (profile in basics.profiles) {
            val profileLayout = inflater.inflate(R.layout.profile_item, profilesContainer, false)
            val profileTextView = profileLayout.findViewById<TextView>(R.id.profileTextView)
            val profileIconImageView = profileLayout.findViewById<ImageView>(R.id.profileIconImageView)

            profileTextView.text = "${profile.network}:     ${profile.url}"
            Picasso.get().load(profile.iconUrl).into(profileIconImageView)

            profilesContainer.addView(profileLayout)
        }
    }
}
