package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SkillsAdapter(private val skills: List<Skill>) : RecyclerView.Adapter<SkillsAdapter.SkillViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_skills, parent, false)
        return SkillViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        val skill = skills[position]
        holder.bind(skill)
    }

    override fun getItemCount(): Int {
        return skills.size
    }

    inner class SkillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val skillNameTextView: TextView = itemView.findViewById(R.id.skillNameTextView)
        private val skillLevelTextView: TextView = itemView.findViewById(R.id.skillLevelTextView)
        private val keywordsTextView: TextView = itemView.findViewById(R.id.keywordsTextView)

        fun bind(skill: Skill) {
            skillNameTextView.text = "Name: ${skill.name}\n"
            skillLevelTextView.text = "Level: ${skill.level}\n"
            keywordsTextView.text = "Keywords: ${skill.keywords.joinToString(", ")}\n"
        }
    }
}
