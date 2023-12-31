package com.example.myapplication

data class ResumeData(
    val basics: Basics,
    val work: List<Work>,
    val education: List<Education>,
    val skills: List<Skill>,
    val references: List<Reference>
)

data class Basics(
    val name: String,
    val label: String,
    val picture: String,
    val email: String,
    val phone: String,
    val website: String,
    val summary: String,
    val location: String,
    val profiles: List<Profile>
)

data class Work(
    val company: String,
    val position: String,
    val website: String,
    val startDate: String,
    val endDate: String,
    val logo: String,
    val summary: String,
    val highlights: List<String>
)

data class Education(
    val institution: String,
    val area: String,
    val studyType: String,
    val startDate: String,
    val endDate: String,
    val gpa: String,
    val courses: List<String>
)

data class Skill(
    val name: String,
    val level: String,
    val keywords: List<String>
)

data class Reference(
    val name: String,
    val reference: String
)

data class Profile(
    val network: String,
    val url: String,
    val iconUrl: String
)
