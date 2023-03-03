package com.example.animalrating

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class AnimalRating : AppCompatActivity() {

    private var itemClicked = 0//global to be used in onCreate and button method. Set from main activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_rating)

        itemClicked = intent.getIntExtra("itemClicked", 0)//setting itemClicked based on main activity
        val sharedPref = getSharedPreferences("ratingInfo", MODE_PRIVATE)//setting shared preferences file

        val animalImage = findViewById<ImageView>(R.id.animal_image_view)//image view field
        val animalRatingBar = findViewById<RatingBar>(R.id.animal_rating_bar)//rating bar
        val animalTitle = findViewById<TextView>(R.id.animal_title)//rating title
        val animalName = findViewById<EditText>(R.id.animal_name_edit_text)//edit text for name field

        //When statement to set up the second activity for each animal. itemClicked comes from the
        //animal clicked in the first activity, so it will display the corresponding image. It will also
        //grab the rating and name from shared preferences that was entered earlier for that animal, and
        //set the fields accordingly
        when(itemClicked){
            0 -> {
                animalImage.setImageResource(R.drawable.dog)
                animalTitle.text = "Rate Dog"//changes title
                val sharedDogName = sharedPref.getString("DogName", "").toString()//grabs animals name
                animalName.setText(sharedDogName)
                animalName.setSelection(0, sharedDogName.length)//highlights th full name for easy change
                val dogStars = sharedPref.getString("DogRating","0")
                if (dogStars != null) {//this if statement was done by android studio
                    animalRatingBar.rating = dogStars.toFloat()//casts the string from previous rating and sets rating bar
                }
            }
            1 -> {
                animalImage.setImageResource(R.drawable.cat)
                animalTitle.text = "Rate Cat"
                val sharedCatName = sharedPref.getString("CatName", "").toString()
                animalName.setText(sharedCatName)
                animalName.setSelection(0, sharedCatName.length)
                val catStars = sharedPref.getString("CatRating","0")
                if (catStars != null) {
                    animalRatingBar.rating = catStars.toFloat()
                }
            }
            2 -> {
                animalImage.setImageResource(R.drawable.bear)
                animalTitle.text = "Rate Bear"
                val sharedBearName = sharedPref.getString("BearName", "").toString()
                animalName.setText(sharedBearName)
                animalName.setSelection(0, sharedBearName.length)
                val bearStars = sharedPref.getString("BearRating","0")
                if (bearStars != null) {
                    animalRatingBar.rating = bearStars.toFloat()
                }
            }
            3 -> {
                animalImage.setImageResource(R.drawable.rabbit)
                animalTitle.text = "Rate Rabbit"
                val sharedRabbitName = sharedPref.getString("RabbitName", "").toString()
                animalName.setText(sharedRabbitName)
                animalName.setSelection(0, sharedRabbitName.length)
                val rabbitStars = sharedPref.getString("RabbitRating","0")
                if (rabbitStars != null) {
                    animalRatingBar.rating = rabbitStars.toFloat()
                }
            }
        }
    }

    /*
    * Method for the submit button. Keeps the information entered through rating bar and edit text in
    * Shared Preferences. Also will start the Main Activity again, and end the second one.
    */
    fun animalSubmitButton(view: View){
        val mainIntent = Intent(this, MainActivity::class.java)//main activity
        val sharedPreferences = getSharedPreferences("ratingInfo", MODE_PRIVATE)//shared pref file
        val editor = sharedPreferences.edit()
        val animalRatingBar = findViewById<RatingBar>(R.id.animal_rating_bar)
        val rating = animalRatingBar.rating.toString()
        val animalName = findViewById<EditText>(R.id.animal_name_edit_text).text.toString()

        when (itemClicked){
            0 ->{
                editor.putString("DogRating", rating)
                editor.putString("DogName", animalName)
                editor.apply()
            }
            1 ->{
                editor.putString("CatRating", rating)
                editor.putString("CatName", animalName)
                editor.apply()
            }
            2 ->{
                editor.putString("BearRating", rating)
                editor.putString("BearName", animalName)
                editor.apply()
            }
            else ->{
                editor.putString("RabbitRating", rating)
                editor.putString("RabbitName", animalName)
                editor.apply()
            }
        }
        startActivity(mainIntent)
    }
}