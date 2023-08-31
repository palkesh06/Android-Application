package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReferencesAdapter(private val references: List<Reference>) :
    RecyclerView.Adapter<ReferencesAdapter.ReferenceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReferenceViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_reference, parent, false)
        return ReferenceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReferenceViewHolder, position: Int) {
        val reference = references[position]
        holder.bind(reference)
    }

    override fun getItemCount(): Int = references.size

    inner class ReferenceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val referenceNameTextView: TextView = itemView.findViewById(R.id.referenceNameTextView)
        private val referenceTextView: TextView = itemView.findViewById(R.id.referenceTextView)
        fun bind(reference: Reference){
            referenceNameTextView.text = "Name: ${reference.name}\n"
            referenceTextView.text = "Reference: ${reference.reference}\n"
        }
    }
}
