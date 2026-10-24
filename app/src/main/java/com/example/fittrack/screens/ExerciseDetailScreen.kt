
package com.example.fittrack.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fittrack.model.Exercise

@Composable
fun ExerciseDetailScreen(
    exercise: Exercise,
    onBack: () -> Unit,
    onLogWorkout: (String, Int) -> Unit
) {
    var duration by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    // Check that the duration is a positive number
    val minutes = duration.toIntOrNull()
    val isValid = minutes != null && minutes > 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("← Back")
        }

        Text(text = exercise.name)
        Text(text = "Difficulty: ${exercise.difficulty}")
        Text(text = "Recommended: ${exercise.recommendedMinutes} minutes")

        // Show the steps for the selected exercise
        exercise.instructions.forEachIndexed { index, step ->
            Text(text = "${index + 1}. $step")
        }

        OutlinedTextField(
            value = duration,
            onValueChange = { duration = it },
            label = { Text("Duration (Minutes)") },
            isError = duration.isNotEmpty() && !isValid,
            modifier = Modifier.fillMaxWidth()
        )

        if (!isValid) {
            Text(
                text = "Enter a valid duration greater than 0",
                color = androidx.compose.material3.MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {
                if (isValid) {
                    onLogWorkout(exercise.name, minutes!!)
                    duration = ""
                    showDialog = true
                }
            },
            enabled = isValid
        ) {
            Text("Log Completed Workout")
        }
    }

    // Confirm when a workout has been saved
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Workout Logged!") },
            text = { Text("Your completed workout has been added to the history.") },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}
