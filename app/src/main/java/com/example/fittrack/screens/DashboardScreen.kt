
package com.example.fittrack.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fittrack.viewmodel.WorkoutUiState

@Composable
fun DashboardScreen(
    uiState: WorkoutUiState,
    onCategoryClick: (String) -> Unit,
    onClearLogs: () -> Unit
) {
    val categories = listOf(
        "Strength", "Cardio", "Flexibility", "HIIT"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "FitTrack Dashboard",
            fontSize = 24.sp
        )

        // Shows the total workouts and minutes
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Total Workouts: ${uiState.totalWorkouts}")
                Text("Total Minutes: ${uiState.totalMinutes}")
            }
        }

        Text(
            text = "Workout Categories",
            fontSize = 20.sp
        )

        // Each category will open its exercise list
        categories.forEach { category ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { onCategoryClick(category) }
            ) {
                Text(
                    text = category,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Recent Activity",
                fontSize = 20.sp
            )

            Button(onClick = onClearLogs) {
                Text("Clear Logs")
            }
        }

        // Shows the newest completed workouts first
        if (uiState.workoutLogs.isEmpty()) {
            Text(
                text = "No workouts logged yet. Select a category above to get started!",
                modifier = Modifier.padding(top = 16.dp)
            )
        } else {
            LazyColumn {
                items(
                    items = uiState.workoutLogs,
                    key = { it.id }
                ) { log ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(log.exerciseName)
                            Text("${log.durationMinutes} minutes")
                        }
                    }
                }
            }
        }
    }
}
