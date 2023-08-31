package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EducationAdapter(private val educationList: List<Education>) : RecyclerView.Adapter<EducationAdapter.EducationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_education, parent, false)
        return EducationViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return educationList.size
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        val education = educationList[position]
        holder.bind(education)
    }

    inner class EducationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val institutionTextView: TextView = itemView.findViewById(R.id.institutionTextView)
        private val areaTextView: TextView = itemView.findViewById(R.id.areaTextView)
        private val studyTypeTextView: TextView = itemView.findViewById(R.id.studyTypeTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val gpaTextView: TextView = itemView.findViewById(R.id.gpaTextView)
        private val coursesTextView: TextView = itemView.findViewById(R.id.coursesTextView)

        fun bind(education: Education) {
            institutionTextView.text = "Institution: ${education.institution}\n"
            areaTextView.text = "Area: ${education.area}\n"
            studyTypeTextView.text = "Study Type: ${education.studyType}\n"
            dateTextView.text = "Date: ${education.startDate} - ${education.endDate}\n"
            gpaTextView.text = "GPA: ${education.gpa}\n"
            if (education.courses.isNotEmpty()) {
                val coursesFormatted = StringBuilder()
                coursesFormatted.append("Courses:\n")

                for (course in education.courses) {
                    coursesFormatted.append("      • $course\n")
                }

                coursesTextView.text = coursesFormatted.toString().trim()
            } else {
                coursesTextView.text = ""
            }
        }
    }
}
