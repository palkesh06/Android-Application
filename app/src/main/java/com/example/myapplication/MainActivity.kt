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
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.BasicsAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var resumeTextView: TextView
    private lateinit var basicsRecyclerView: RecyclerView
    private lateinit var workRecyclerView: RecyclerView
    private lateinit var educationRecyclerView: RecyclerView
    private lateinit var skillsRecyclerView: RecyclerView
    private lateinit var referencesRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resumeTextView = findViewById(R.id.resumeTextView)
        basicsRecyclerView = findViewById(R.id.basicsRecyclerView)
        workRecyclerView = findViewById(R.id.workRecyclerView)
        educationRecyclerView = findViewById(R.id.educationRecyclerView)
        skillsRecyclerView = findViewById(R.id.skillsRecyclerView)
        referencesRecyclerView = findViewById(R.id.referencesRecyclerView)

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
                    val gson = Gson()
                    val resumeData = gson.fromJson(json, ResumeData::class.java)

                    runOnUiThread {
                        val basicsAdapter = BasicsAdapter(resumeData.basics)
                        val workAdapter = WorkAdapter(resumeData.work)
                        val educationAdapter = EducationAdapter(resumeData.education)
                        val skillsAdapter = SkillsAdapter(resumeData.skills)
                        val referenceAdapter = ReferencesAdapter(resumeData.references)

                        workRecyclerView.adapter = workAdapter
                        basicsRecyclerView.adapter = basicsAdapter
                        educationRecyclerView.adapter = educationAdapter
                        skillsRecyclerView.adapter = skillsAdapter
                        referencesRecyclerView.adapter = referenceAdapter

                        workRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        basicsRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        educationRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        skillsRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        referencesRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }
        })
    }

//    private fun displayResume(json: String?) {
//
//
//        GlobalScope.launch(Dispatchers.Main) {
//            val formattedResume = buildString {
//                appendHeading("Basics")
//                append("Name: ${resumeData.basics.name}\n")
//                append(("Label: ${resumeData.basics.label}\n"))
//                appendHyperlink("Website:", resumeData.basics.website)
//                append("Email:  ${resumeData.basics.email}\n")
//                append("Phone: ${resumeData.basics.phone}\n\n")
//                append("Summary: ${resumeData.basics.summary}\n\n")
//                append("Location: ${resumeData.basics.location}\n")
//                appendHeading("Profiles")
//                for (profile in resumeData.basics.profiles) {
//                    appendProfile(profile)
//                }
//                appendHeading("Work Experience")
//                for (experience in resumeData.work) {
//                    append("Company: ${experience.company}\n")
//                    append("Position: ${experience.position}\n")
//                    append("Start Date: ${experience.startDate}\n")
//                    append("End Date: ${experience.endDate}\n")
//                    append("Summary: ${experience.summary}\n")
//                    append("Highlights:\n")
//                    for (highlight in experience.highlights) {
//                        appendBulletPoint(highlight)
//                    }
//                    append("\n")
//                }
//
//                appendHeading("Education")
//                for (education in resumeData.education) {
//                    append("Institution: ${education.institution}\n")
//                    append("Area: ${education.area}\n")
//                    append("Study Type: ${education.studyType}\n")
//                    append("Start Date: ${education.startDate}\n")
//                    append("End Date: ${education.endDate}\n")
//                    append("GPA: ${education.gpa}\n")
//                    append("Courses:\n")
//                    for (course in education.courses) {
//                        appendBulletPoint(course)
//                    }
//                    append("\n")
//                }
//
//                appendHeading("Skills")
//                for (skill in resumeData.skills) {
//                    append("Name: ${skill.name}\n")
//                    append("Level: ${skill.level}\n")
//                    append("Keywords: ${skill.keywords.joinToString(", ")}\n")
//                    append("\n")
//                }
//
//                appendHeading("References")
//                for (reference in resumeData.references) {
//                    append("Name: ${reference.name}\n")
//                    append("Reference: ${reference.reference}\n")
//                    append("\n")
//                }
//            }
//
//            resumeTextView.text = formattedResume
//            val profileImageUrl = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?cs=srgb&dl=pexels-pixabay-220453.jpg&fm=jpg"
//            if (!profileImageUrl.isNullOrEmpty()) {
//                runOnUiThread {
//                    Picasso.get()
//                        .load(profileImageUrl)
//                        .error(R.drawable.ic_launcher_background) // Load placeholder image on error
//                        .into(profileImageView)
//                }
//            } else {
//                // Load the placeholder image if the profile image URL is empty or null
//                runOnUiThread {
//                    Picasso.get().load(R.drawable.ic_launcher_background).into(profileImageView)
//                }
//            }
//            resumeTextView.movementMethod = LinkMovementMethod.getInstance()
//        }
//    }
//
//    private fun StringBuilder.appendHeading(heading: String) {
//        append("\n\n")
//        append("### $heading ###\n")
//        append("------------------------------\n")
//    }
//
//    private fun StringBuilder.appendBulletPoint(item: String) {
//        append("• $item\n")
//    }
//
//    private fun StringBuilder.appendHyperlink(label: String, url: String) {
//        append("$label $url\n")
//    }
//    private fun StringBuilder.appendProfile(profile: Profile) {
//        append("Network: ${profile.network}\n")
//        appendHyperlink("Profile URL:", profile.url)
//        append("\n")
//    }

}
