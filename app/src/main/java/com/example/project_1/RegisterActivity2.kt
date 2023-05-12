package com.example.project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent;
import android.view.View;
import android.widget.Button
import android.widget.EditText;
import android.widget.TextView
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

private lateinit var etUsername: EditText;
private lateinit var etPassword: EditText;
private lateinit var etButton: Button;

private lateinit var userName: String
private lateinit var password: String


class RegisterActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //var register = findViewById(R.id.btnRegister) as Button
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)


        etUsername = findViewById<EditText>(R.id.etRUserName)
        etPassword = findViewById<EditText>(R.id.etRPassword)

        etButton = findViewById<Button>(R.id.btnRegister)
        this.findViewById<TextView>(R.id.tvLoginLink).setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        etButton.setOnClickListener {
            registerUser();
        }


    }

    private fun registerUser() {
        val userName: String = etUsername.getText().toString().trim()
        val password: String = etPassword.getText().toString().trim()
        if (userName.isEmpty()) {
            etUsername.setError("Username is required")
            etUsername.requestFocus()
            return
        } else if (password.isEmpty()) {
            etPassword.setError("Password is required")
            etPassword.requestFocus()
            return
        }
        val call: Call<ResponseBody> = RetrofitClient
            .getInstance()
            .api
            .createUser(User(userName, password))
        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>) {
                var s = ""

                try {
                    s = response.body()!!.string()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                if (s == "SUCCESS") {

                    Toast.makeText(
                        this@RegisterActivity2,
                        "Successfully registered. Please login",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this@RegisterActivity2, LoginActivity::class.java))
                } else {
                    Toast.makeText(this@RegisterActivity2, "User already exists!", Toast.LENGTH_LONG)
                        .show()
                }

            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@RegisterActivity2, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}