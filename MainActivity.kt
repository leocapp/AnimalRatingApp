package com.example.animalrating

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayRatings() //displays the ratings and names on home-screen

        val animalIntent = Intent(this, AnimalRating::class.java) //Second activity intent

        //list of animals for the image buttons, all have an index
        val imageIdList = listOf(
            R.id.dog_button,
            R.id.cat_button,
            R.id.bear_button,
            R.id.rabbit_button
        )//end list

        //loop for each button, depending on the index
        // each page will open up with corresponding picture based on item clicked
        for ((index, imageId) in imageIdList.withIndex()) {
            findViewById<ImageButton>(imageId).setOnClickListener {
                animalIntent.putExtra("itemClicked", index)
                startActivity(animalIntent)
            }//end onClickListener
        }//end for loop
    }//end onCreate

    /*
    * Method for the Clear Ratings Button. This will clear all the data in shared preferences.
    * It also clears the home screen of the previous information
    * */
    fun clearDataButton(view: View){
        val sharedPreferences = getSharedPreferences("ratingInfo", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.remove("DogRating").commit()
        editor.remove("CatRating").commit()
        editor.remove("BearRating").commit()
        editor.remove("RabbitRating").commit()
        editor.remove("DogName").commit()
        editor.remove("CatName").commit()
        editor.remove("BearName").commit()
        editor.remove("RabbitName").commit()
        displayRatings()
    }

    /*
    * Method for displaying the home screen info after setting the rating and
    * naming each animal. Takes no parameters just changes the textviews to new information.
    * */
    private fun displayRatings(){

        val sharedPref = getSharedPreferences("ratingInfo", MODE_PRIVATE)

        //Dog Home Display
        var dogRatingText = findViewById<TextView>(R.id.dog_rating_description)
        var dogNameText = findViewById<TextView>(R.id.dog_name_text_view)
        val dogRating = sharedPref.getString("DogRating","")
        val dogName = sharedPref.getString("DogName","")
        dogRatingText.text = "Your Rating: $dogRating"
        dogNameText.text = "$dogName"

        //Cat Home Display
        var catRatingText = findViewById<TextView>(R.id.cat_rating_description)
        var catNameText = findViewById<TextView>(R.id.cat_name_text_view)
        val catRating = sharedPref.getString("CatRating","")
        val catName = sharedPref.getString("CatName","")
        catRatingText.text = "Your Rating: $catRating"
        catNameText.text = "$catName"

        //Bear Home Display
        var bearRatingText = findViewById<TextView>(R.id.bear_rating_description)
        var bearNameText = findViewById<TextView>(R.id.bear_name_text_view)
        val bearRating = sharedPref.getString("BearRating","")
        val bearName = sharedPref.getString("BearName","")
        bearRatingText.text = "Your Rating: $bearRating"
        bearNameText.text = "$bearName"

        //Rabbit Home Display
        var rabbitRatingText = findViewById<TextView>(R.id.rabbit_rating_description)
        var rabbitNameText = findViewById<TextView>(R.id.rabbit_name_text_view)
        val rabbitRating = sharedPref.getString("RabbitRating","")
        val rabbitName = sharedPref.getString("RabbitName","")
        rabbitRatingText.text = "Your Rating: $rabbitRating"
        rabbitNameText.text = "$rabbitName"

    }

}