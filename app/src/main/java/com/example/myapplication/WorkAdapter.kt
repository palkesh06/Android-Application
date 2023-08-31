package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class WorkAdapter(private var workList: List<Work>) : RecyclerView.Adapter<WorkAdapter.WorkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_work, parent, false)
        return WorkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {
        val work = workList[position]
        holder.bind(work)
    }

    override fun getItemCount(): Int {
        return workList.size
    }
    inner class WorkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val companyTextView: TextView = itemView.findViewById(R.id.companyTextView)
        private val positionTextView: TextView = itemView.findViewById(R.id.positionTextView)
        private val startDateTextView: TextView = itemView.findViewById(R.id.startDateTextView)
        private val endDateTextView: TextView = itemView.findViewById(R.id.endDateTextView)
        private val logoImageView: ImageView = itemView.findViewById(R.id.logoImageView)
        private val summaryTextView: TextView = itemView.findViewById(R.id.summaryTextView)
        private val highlightsTextView: TextView = itemView.findViewById(R.id.highlightsTextView)
        fun bind(work: Work) {
            companyTextView.text = work.company
            positionTextView.text = "Position: ${ work.position }\n"
            startDateTextView.text = "Start Date: ${work.startDate}\n"
            endDateTextView.text = "End Date: ${work.endDate}\n"
            summaryTextView.text = "Summary: ${work.summary}\n"
            if (work.highlights.isNotEmpty()) {
                val highlightsFormatted = StringBuilder()
                highlightsFormatted.append("Highlights:\n")

                for (highlight in work.highlights) {
                    highlightsFormatted.append("  • $highlight\n")
                }

                highlightsTextView.text = highlightsFormatted.toString().trim()
            } else {
                highlightsTextView.text = ""
            }

            // Load logo using Picasso or Glide
            Picasso.get().load(work.logo).into(logoImageView)
        }
    }
}



