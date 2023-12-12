package com.example.pizzaapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.pizzaapp.model.MenuModel

class EditMenuActivity : AppCompatActivity() {
    lateinit var image : ImageView
    companion object{
        val IMAGE_REQUEST_CODE = 100
        var idMakanan = 1
        var namaMakanan = "test"
        var hargaMakanan = 10000
        lateinit var gambarMakanan : Bitmap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_menu)

        //hide title bar
        getSupportActionBar()?.hide()

        //instance
        image = findViewById(R.id.imageMenu)
        val textId : EditText = findViewById(R.id.menuId)
        val textName : EditText = findViewById(R.id.menuName)
        val textPrice : EditText = findViewById(R.id.menuPrice)
        val btnAddImage : Button = findViewById(R.id.buttonAddImage)
        val btnUpdate : Button = findViewById(R.id.buttonUpdateMenu)

        //set data
        textId.setText(idMakanan.toString())
        textName.setText(namaMakanan)
        textPrice.setText(hargaMakanan.toString())
        image.setImageBitmap(gambarMakanan)

        //event saat button add (+) di klik
        btnAddImage.setOnClickListener{
            pickImageGalery()
        }

        btnUpdate.setOnClickListener{
            //object class databaseHelper
            val databaseHelper = DatabaseHelper(this)

            val id : Int = textId.text.toString().toInt()
            val name : String = textName.text.toString().trim()
            val price : Int = textPrice.text.toString().toInt()
            val bitmapDrawable : BitmapDrawable = image.drawable as BitmapDrawable
            val bitmap : Bitmap = bitmapDrawable.bitmap

            val menuModel = MenuModel(id,name,price,bitmap)
            databaseHelper.updateMenu(menuModel)

            //intent main activity
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }


    }
    private fun pickImageGalery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/"
        startActivityForResult(intent, AddMenuActivity.IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == AddMenuActivity.IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            image.setImageURI(data?.data)
        }
    }
}