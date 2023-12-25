package com.farlan.readinglist

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_book)

        val book = intent.getParcelableExtra<Book>(MainActivity.INTENT_PARCELABLE)

        val title = findViewById<TextView>(R.id.tv_title)
        val author = findViewById<TextView>(R.id.tv_author)
        val publisher = findViewById<TextView>(R.id.tv_publisher)
        val releaseYear = findViewById<TextView>(R.id.tv_release_year)
        val description = findViewById<TextView>(R.id.tv_description)
        val cover = findViewById<ImageView>(R.id.img_cover)

        title.text = book?.title
        author.text = book?.author
        publisher.text = book?.publisher
        releaseYear.text = book?.releaseYear
        description.text = book?.description
        cover.setImageResource(book?.cover!!)
    }
}