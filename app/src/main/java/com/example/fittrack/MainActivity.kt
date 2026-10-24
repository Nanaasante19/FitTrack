package com.example.fittrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fittrack.ui.theme.FitTrackTheme
import androidx.compose.material3.Card
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.example.fittrack.model.Workout
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.fittrack.screens.ExerciseListScreen
import com.example.fittrack.model.Exercise
import com.example.fittrack.screens.ExerciseDetailScreen
import com.example.fittrack.viewmodel.WorkoutViewModel
import com.example.fittrack.screens.DashboardScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val workoutViewModel = androidx.lifecycle.ViewModelProvider(this)[WorkoutViewModel::class.java]
        setContent {
            FitTrackTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        var currentScreen by rememberSaveable {
                            mutableStateOf("workouts")
                        }
                        var selectedExercise by remember {
                            mutableStateOf<Exercise?>(null)
                        }
// Keeps track of the workout category selected on the dashboard
                        var selectedCategory by rememberSaveable {
                            mutableStateOf("Strength")
                        }
                        when (currentScreen) {
                            "workouts" -> {
                                DashboardScreen(
                                    uiState = workoutViewModel.uiState,
                                    onCategoryClick = { category ->
                                        selectedCategory = category
                                        currentScreen = "exercises"
                                    },
                                    onClearLogs = {
                                        workoutViewModel.clearLogs()
                                    }
                                )
                            }

                            "exercises" -> {
                                Column {
                                    Button(onClick = { currentScreen = "workouts" }) {
                                        Text("Back to Workouts")
                                    }

                                    ExerciseListScreen(
                                        category = selectedCategory,
                                        onExerciseClick = { exercise ->
                                            selectedExercise = exercise
                                            currentScreen = "details"

                                        }
                                    )
                                }
                            }
                            "details" -> {
                                selectedExercise?.let { exercise ->
                                    ExerciseDetailScreen(
                                        exercise = exercise,
                                        onBack = {
                                            currentScreen = "exercises"
                                        },
                                        onLogWorkout = { name, minutes ->
                                            workoutViewModel.logWorkout(name, minutes)

                                        }
                                    )
                                }
                            }
                        }
                    }



                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    androidx.compose.foundation.layout.Column(modifier = modifier) {
        Text(text = "Welcome to FitTrack!")
        Text(text = "Learning Jetpack Compose!")
    }
}

// Displays a practice workout card on the screen
@Composable
fun PracticeCard() {
    Card(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "My first Card",
            modifier = Modifier.padding(16.dp)
        )
    }
}
@Composable
fun PracticeRow() {
    Row {
        Text(text = "Left")
        Text(text = "Right")
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FitTrackTheme {
        Greeting("Android")
    }
}

@Composable
fun WorkoutScreen() {

    var workoutCount by remember { mutableIntStateOf(0) }

    var workoutName by remember { mutableStateOf("") }

    var workoutDuration by remember { mutableStateOf("") }

    var savedWorkoutName by remember { mutableStateOf("") }
    var savedWorkoutDuration by remember { mutableStateOf("") }

    val workout = Workout(
        id = 1,
        name = "Morning Workout",
        durationMinutes = 30,
        caloriesBurned = 200
    )

    Card {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "FitTrack Workouts")

            OutlinedTextField(
                value = workoutName,
                onValueChange = { workoutName = it },
                label = { Text("Workout Name") }
            )
            OutlinedTextField(
                value = workoutDuration,
                onValueChange = { workoutDuration = it },
                label = { Text("Duration (minutes)") }
            )



            Text(text = "Workout: ${savedWorkoutName.ifEmpty { workout.name }}")
            Text(text = "Duration: ${savedWorkoutDuration.ifEmpty { workout.durationMinutes.toString() }} minutes")
            Text(text = "Calories: ${workout.caloriesBurned} kcal")
            Text(text = "Workouts Added: $workoutCount")


            Button(onClick = {
                val minutes = workoutDuration.toIntOrNull()
                if (workoutName.isNotBlank() && minutes != null && minutes > 0) {
                    savedWorkoutName = workoutName.trim()
                    savedWorkoutDuration = minutes.toString()
                    workoutCount++
                    workoutName = ""
                    workoutDuration = ""
                }
            }) {
                Text("Add Workout")
            }



        }
    }
}
