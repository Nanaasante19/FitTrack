
package com.example.fittrack.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// Information about a completed workout
data class WorkoutLog(
    val id: Int,
    val exerciseName: String,
    val durationMinutes: Int
)

// Stores the data shared between our screens
data class WorkoutUiState(
    val workoutLogs: List<WorkoutLog> = emptyList(),
    val totalWorkouts: Int = 0,
    val totalMinutes: Int = 0
)

class WorkoutViewModel : ViewModel() {

    // Keeps track of the current workout information
    var uiState by mutableStateOf(WorkoutUiState())
        private set

    private var nextLogId = 1

    // Saves a workout and updates the dashboard totals
    fun logWorkout(exerciseName: String, durationMinutes: Int) {
        if (durationMinutes <= 0) return

        val newLog = WorkoutLog(
            id = nextLogId++,
            exerciseName = exerciseName,
            durationMinutes = durationMinutes
        )

        uiState = uiState.copy(
            workoutLogs = listOf(newLog) + uiState.workoutLogs,
            totalWorkouts = uiState.totalWorkouts + 1,
            totalMinutes = uiState.totalMinutes + durationMinutes
        )
    }

    // Resets the workout history and statistics
    fun clearLogs() {
        uiState = WorkoutUiState()
    }
}
