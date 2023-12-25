package com.farlan.readinglist

import android.content.Intent
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailBookActivity : AppCompatActivity() {

    private lateinit var bookTitle: String
    private lateinit var bookAuthor: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_book)

        val book = intent.getParcelableExtra<Book>(MainActivity.INTENT_PARCELABLE)

        bookTitle = book?.title ?: ""
        bookAuthor = book?.author ?: ""

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareButton -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                val shareBody = "Check out this book: $bookTitle by $bookAuthor"
                val shareSubject = "Farlan Reading List"
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject)
                startActivity(Intent.createChooser(sharingIntent, "Share using"))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}