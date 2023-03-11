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
    private val animalImages = listOf(
        R.drawable.dog,
        R.drawable.cat,
        R.drawable.bear,
        R.drawable.rabbit
    )//global list of images
    private val animalNames = listOf("Dog", "Cat", "Bear", "Rabbit")//global list of names

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_rating)

        itemClicked = intent.getIntExtra("itemClicked", 0)//setting itemClicked based on main activity
        val sharedPref = getSharedPreferences("ratingInfo", MODE_PRIVATE)//setting shared preferences file

        val animalImage = findViewById<ImageView>(R.id.animal_image_view)//image view field
        val animalRatingBar = findViewById<RatingBar>(R.id.animal_rating_bar)//rating bar
        val animalTitle = findViewById<TextView>(R.id.animal_title)//rating title
        val animalName = findViewById<EditText>(R.id.animal_name_edit_text)//edit text for name field

        //itemClicked comes from the
        //animal clicked in the first activity, so it will display the corresponding image. It will also
        //grab the rating and name from shared preferences that was entered earlier for that animal, and
        //set the fields accordingly

        animalImage.setImageResource(animalImages[itemClicked])//setting the image based on item clicked
        animalTitle.text = "Rate ${animalNames[itemClicked]}"//setting th title based on item clicked

        val sharedName = sharedPref.getString("${animalNames[itemClicked]}Name", "").toString()
        animalName.setText(sharedName)
        animalName.setSelection(0, sharedName.length)
        val stars = sharedPref.getString("${animalNames[itemClicked]}Rating","0")
        if (stars != null) {//this if statement was done by android studio
            animalRatingBar.rating = stars.toFloat()//casts the string from previous rating and sets rating bar
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

        editor.putString("${animalNames[itemClicked]}Rating", rating)
        editor.putString("${animalNames[itemClicked]}Name", animalName)
        editor.apply()

        startActivity(mainIntent)
    }
}