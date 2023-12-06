package com.example.pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hide title bar
        getSupportActionBar()?.hide()

        //instance text
        val txtUsername:EditText = findViewById(R.id.editTextEmail)
        val txtPassword:EditText = findViewById(R.id.editTextPassword)

        //instance button login
        val btnLogin: Button = findViewById(R.id.buttonLogin)
        val btnDontHave:TextView = findViewById(R.id.dontHave)

        //event button dont have
        btnDontHave.setOnClickListener {
            val intentRegister = Intent(this,RegisterActivity::class.java)
            startActivity(intentRegister)
        }

        //event button login
        btnLogin.setOnClickListener {

            //instance
            val dbHelper = DatabaseHelper(this)
            val result:Boolean = dbHelper.checkLogin(txtUsername.text.toString(), txtPassword.text.toString())

            if(result){
                val intentLogin = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intentLogin)
            }else {
                Toast.makeText(this@LoginActivity, "Login gagal, coba lagi !!", Toast.LENGTH_SHORT).show()
            }

            //check data
            val data:String? = dbHelper.checkData(txtUsername.text.toString())
            Toast.makeText(this@LoginActivity, "Result : " + data, Toast.LENGTH_SHORT).show()
            if (data==null) {
                //insert data
                dbHelper.addAccount("damaradii.nugroho@gmail.com", "damar adi nugroho", "cashier", "12345")
            }
        }
    }
}