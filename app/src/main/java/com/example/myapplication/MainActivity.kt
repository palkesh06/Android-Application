package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException
import android.text.method.LinkMovementMethod

class MainActivity : AppCompatActivity() {

    private lateinit var resumeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resumeTextView = findViewById(R.id.resumeTextView)

        fetchResume()
    }

    private fun fetchResume() {
        val client = OkHttpClient()
        val url = "https://gist.githubusercontent.com/sarangmorde/dce007786d950dc48556da4f690f3090/raw/df9d5d6a39e9468af3fcd90d59eade0f409f6eaf/resume.json"
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val json = response.body?.string()
                    displayResume(json)
                }
            }
        })
    }

    private fun displayResume(json: String?) {
        val gson = Gson()
        val resumeData = gson.fromJson(json, ResumeData::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val formattedResume = buildString {
                appendHeading("Basics")
                append("Name: ${resumeData.basics.name}\n")
                appendHyperlink("Email", resumeData.basics.email)
                append("Phone: ${resumeData.basics.phone}\n")
                append("Location: ${resumeData.basics.location}\n")
                // Add other basic information

                appendHeading("Work Experience")
                for (experience in resumeData.work) {
                    append("Company: ${experience.company}\n")
                    append("Position: ${experience.position}\n")
                    append("Start Date: ${experience.startDate}\n")
                    append("End Date: ${experience.endDate}\n")
                    append("Summary: ${experience.summary}\n")
                    append("Highlights:\n")
                    for (highlight in experience.highlights) {
                        appendBulletPoint(highlight)
                    }
                    append("\n")
                }

                appendHeading("Education")
                for (education in resumeData.education) {
                    append("Institution: ${education.institution}\n")
                    append("Area: ${education.area}\n")
                    append("Study Type: ${education.studyType}\n")
                    append("Start Date: ${education.startDate}\n")
                    append("End Date: ${education.endDate}\n")
                    append("GPA: ${education.gpa}\n")
                    append("Courses:\n")
                    for (course in education.courses) {
                        appendBulletPoint(course)
                    }
                    append("\n")
                }

                appendHeading("Skills")
                for (skill in resumeData.skills) {
                    append("Name: ${skill.name}\n")
                    append("Level: ${skill.level}\n")
                    append("Keywords: ${skill.keywords.joinToString(", ")}\n")
                    append("\n")
                }

                appendHeading("References")
                for (reference in resumeData.references) {
                    append("Name: ${reference.name}\n")
                    append("Reference: ${reference.reference}\n")
                    append("\n")
                }
            }

            resumeTextView.text = formattedResume
            resumeTextView.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun StringBuilder.appendHeading(heading: String) {
        append("\n\n")
        append("### $heading ###\n")
        append("------------------------------\n")
    }

    private fun StringBuilder.appendBulletPoint(item: String) {
        append("• $item\n")
    }

    private fun StringBuilder.appendHyperlink(label: String, url: String) {
        append("[$label]($url)\n")
    }

}
