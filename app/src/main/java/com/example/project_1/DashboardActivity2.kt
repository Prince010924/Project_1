package com.example.project_1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
private lateinit var welcomeText: String
private lateinit var tvWelcome: TextView



class DashboardActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard2)



        welcomeText = "Welcome " + intent.getStringExtra("username") + "!";
        tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText(welcomeText);
    }
}