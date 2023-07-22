package com.example.guess;



import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private EditText editTextAnswer;
    private TextView textViewScore;
    private TextView textViewTimer;

    private String selectedTopic;
    private int score = 0;
    private int questionIndex = 0;
    private CountDownTimer timer;

    // Example array of questions (you can replace this with your own data source)
    private String[] questions = {
            " What is 8 * 7?",
            "Which country has the largest population?",
            "Who painted the Mona Lisa?",
            "What is 2^3 ?",
            "How many countries are in Africa ?",
            "Who is the current president of the United States ?",
            "What is the capital of Canada?",
            "What is 56-24?",
            "When was america discovered ?",

            // Add more questions here
    };

    // Example array of corresponding answers
    private String[] answers = {
            "56", // Answer to Question 1
            "China", // Answer to Question 2
            "Leonardo da Vinci", // Answer to Question 3
            "8",
            "56",
            "Joe Biden",
            "Ottawa",
            "32",
            "1492",
            // Add more answers here
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_guess);

        // Get the selected topic from the MainActivity's intent
        selectedTopic = getIntent().getStringExtra("selected_topic");

        // Initialize UI elements
        textViewQuestion = findViewById(R.id.textViewQuestion);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        textViewScore = findViewById(R.id.textViewScore);
        textViewTimer = findViewById(R.id.textViewTimer);

        // Start the game with the first question
        showNextQuestion();
    }

    // Method to display the next question
    private void showNextQuestion() {
        if (questionIndex < questions.length) {
            textViewQuestion.setText(questions[questionIndex]);
            editTextAnswer.setText("");
            startTimer(15); // Start the 15-second timer for each question
        } else {
            // No more questions, game is over
            gameOver();
        }
    }

    // Method to handle the user's answer submission
    public void submitAnswer(View view) {
        String userAnswer = editTextAnswer.getText().toString().trim();

        // Check if the user's answer is correct
        if (userAnswer.equalsIgnoreCase(answers[questionIndex])) {
            score += 5; // Correct answer, add 5 points to the score
            textViewScore.setText("Score: " + score); // Update the score TextView
        }

        questionIndex++; // Move to the next question
        if (questionIndex < questions.length) {
            // Show the next question after a short delay
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showNextQuestion();
                }
            }, 1000); // Delay for 1 second before showing the next question
        } else {
            // No more questions, game is over
            gameOver();
        }
    }

    // Method to start the timer for each question
    private void startTimer(int seconds) {
        if (timer != null) {
            timer.cancel(); // Cancel the previous timer if there is any
        }
        timer = new CountDownTimer(seconds * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText("Time left: " + millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                textViewTimer.setText("Time's up!");
                questionIndex++; // Move to the next question after the timer runs out
                showNextQuestion();
            }
        }.start();
    }

    // Method to handle the game over scenario
    private void gameOver() {
        // Display the final score and end the game
        textViewQuestion.setText("Game Over!");
        editTextAnswer.setVisibility(View.GONE);
        findViewById(R.id.buttonSubmit).setVisibility(View.GONE);
        textViewScore.setText("Final Score: " + score);
        textViewTimer.setVisibility(View.GONE);
        if (timer != null) {
            timer.cancel(); // Cancel the timer if the game is over
        }
    }
}
