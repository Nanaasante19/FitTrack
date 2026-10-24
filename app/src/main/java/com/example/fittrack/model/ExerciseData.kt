package com.example.fittrack.model

// Exercises available in the FitTrack app
val exercises = listOf(
    Exercise(
        1, "Classic Push-ups", "Strength", "Beginner",
        "Builds upper body strength using body weight.",
        listOf(
            "Start in a plank position.",
            "Lower your chest toward the floor.",
            "Push back up slowly."
        ),
        10
    ),
    Exercise(
        2, "Bodyweight Squats", "Strength", "Beginner",
        "Strengthens your legs and hips.",
        listOf(
            "Stand with your feet shoulder-width apart.",
            "Bend your knees and lower your hips.",
            "Stand back up."
        ),
        10
    ),
    Exercise(
        3, "Plank Hold", "Strength", "Intermediate",
        "Improves core stability.",
        listOf(
            "Rest on your forearms and toes.",
            "Keep your body in a straight line.",
            "Hold the position while breathing normally."
        ),
        5
    ),
    Exercise(
        4, "Brisk Walking", "Cardio", "Beginner",
        "Improves cardiovascular endurance.",
        listOf(
            "Start walking at a comfortable pace.",
            "Gradually increase your speed.",
            "Maintain a steady pace."
        ),
        20
    ),
    Exercise(
        5, "Jogging", "Cardio", "Intermediate",
        "Builds aerobic fitness.",
        listOf(
            "Warm up with a short walk.",
            "Jog at a comfortable pace.",
            "Slow down to cool off."
        ),
        15
    ),
    Exercise(
        6, "Marching in Place", "Cardio", "Beginner",
        "Raises your heart rate with gentle movement.",
        listOf(
            "Stand upright.",
            "Lift your knees alternately.",
            "Swing your arms naturally."
        ),
        10
    ),
    Exercise(
        7, "Shoulder Stretch", "Flexibility", "Beginner",
        "Improves shoulder mobility.",
        listOf(
            "Bring one arm across your chest.",
            "Hold it gently with your other arm.",
            "Switch sides."
        ),
        5
    ),
    Exercise(
        8, "Hamstring Stretch", "Flexibility", "Beginner",
        "Stretches the muscles behind your thighs.",
        listOf(
            "Sit with one leg extended.",
            "Lean forward gently.",
            "Switch legs."
        ),
        5
    ),
    Exercise(
        9, "Cat-Cow Stretch", "Flexibility", "Beginner",
        "Encourages gentle spinal movement.",
        listOf(
            "Start on your hands and knees.",
            "Gently arch your back.",
            "Slowly round your back and repeat."
        ),
        5
    ),
    Exercise(
        10, "Jumping Jacks", "HIIT", "Intermediate",
        "A full-body cardio exercise.",
        listOf(
            "Stand with your arms by your sides.",
            "Jump your feet apart while raising your arms.",
            "Return to the starting position."
        ),
        5
    ),
    Exercise(
        11, "High Knees", "HIIT", "Advanced",
        "A fast-paced cardio movement.",
        listOf(
            "Stand upright.",
            "Lift your knees alternately.",
            "Move at a comfortable, controlled pace."
        ),
        5
    ),
    Exercise(
        12, "Step Jacks", "HIIT", "Beginner",
        "A lower-impact alternative to jumping jacks.",
        listOf(
            "Step one foot out to the side.",
            "Raise both arms overhead.",
            "Return and alternate sides."
        ),
        5
    )
)