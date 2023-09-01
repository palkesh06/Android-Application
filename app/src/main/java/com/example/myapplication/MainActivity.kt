package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        val cacheSize: Long = 10 * 1024 * 1024 // 10 MB
        val cache = Cache(applicationContext.cacheDir, cacheSize)
        val client = OkHttpClient.Builder()
            .cache(cache)
            .build()
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
}
