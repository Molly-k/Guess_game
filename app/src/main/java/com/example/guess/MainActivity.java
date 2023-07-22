package com.example.guess;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Array of topic names
    private String[] topics = {"Maths", "Geography", "Music", "Logo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startGame(View view) {
        // Start the GameActivity
        Intent gameIntent = new Intent(this, GameActivity.class);
        startActivity(gameIntent);
    }

    public void openSettings(View view) {
        // Implement the logic to open the settings activity here
        // For example, start a new activity for settings using Intent
        Intent settingsIntent = new Intent(this,SettingsActivity .class);
        startActivity(settingsIntent);
    }
}
