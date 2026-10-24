package com.example.fittrack.model

// Stores the details of each exercise
data class Exercise(
    val id: Int,
    val name: String,
    val category: String,
    val difficulty: String,
    val preview: String,
    val instructions: List<String>,
    val recommendedMinutes: Int
)