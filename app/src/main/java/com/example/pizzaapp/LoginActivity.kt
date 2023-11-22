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

        //event button login
        btnLogin.setOnClickListener {
            //instance
            val dbHelper = DatabaseHelper(this)
            var result:Boolean = dbHelper.checkLogin(txtUsername.text.toString(),txtPassword.text.toString())
            if (result){
                val intentLogin = Intent( this@LoginActivity,
                    MainActivity::class.java)
                startActivity(intentLogin)
            }else{
                Toast.makeText(this@LoginActivity,"Login failed. Try Again",
                    Toast.LENGTH_SHORT).show()
                txtUsername.hint = "email"
                txtPassword.hint = "password"
            }

///////////////////////////////////////////////////////////////////////////////////////
//            //check data
//            val data: String = dbHelper.checkData("muttaqien0111@gmail.com")
//            Toast.makeText(this@LoginActivity, "Result : " + data,
//                Toast.LENGTH_SHORT).show()
//            if(data==null){
//                //insert data
//                dbHelper.addAccount("muttaqien0111@gmail.com", "Native Muttaqien", "Cashier", "123")
//            }
///////////////////////////////////////////////////////////////////////////////////////

            //check data
            //FIXED REGISTER (GANTI String ke String?)
            val data: String? = dbHelper.checkData(txtUsername.text.toString())
            Toast.makeText(this@LoginActivity, "Result : " + data,
                Toast.LENGTH_SHORT).show()
            if(data==null){
                //insert data
                dbHelper.addAccount(txtUsername.text.toString(), "Native Muttaqien", "Cashier", txtPassword.text.toString())
            }
        }

        //event button dont have
        btnDontHave.setOnClickListener {
            val intentRegister = Intent(this,RegisterActivity::class.java)
            startActivity(intentRegister)
        }
    }
}