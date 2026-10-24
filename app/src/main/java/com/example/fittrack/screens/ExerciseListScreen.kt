package com.example.fittrack.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fittrack.model.Exercise
import com.example.fittrack.model.exercises

@Composable
fun ExerciseListScreen(
    category: String,
    onExerciseClick: (Exercise) -> Unit
) {
    val filteredExercises = exercises.filter {
        it.category == category
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "$category Exercises")

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredExercises) { exercise ->
                Card(
                    modifier = Modifier.clickable {
                        onExerciseClick(exercise)
                    }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = exercise.name)
                        Text(text = exercise.preview)
                        Text(text = "Difficulty: ${exercise.difficulty}")
                        Text(text = "Recommended: ${exercise.recommendedMinutes} minutes")
                    }
                }
            }
        }
    }
}