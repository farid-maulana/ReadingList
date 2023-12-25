package com.farlan.readinglist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.farlan.readinglist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var rvBooks: RecyclerView
    private val list = ArrayList<Book>()
    private lateinit var binding: ActivityMainBinding

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvBooks = findViewById(R.id.rv_reading_list)
        rvBooks.setHasFixedSize(true)

        list.addAll(getListBooks())
        showRecyclerList()
    }

    private fun getListBooks(): ArrayList<Book> {
        val dataTitle = resources.getStringArray(R.array.data_book_title)
        val dataAuthor = resources.getStringArray(R.array.data_book_author)
        val dataPublisher = resources.getStringArray(R.array.data_book_publisher)
        val dataReleaseYear = resources.getStringArray(R.array.data_book_release_year)
        val dataDescription = resources.getStringArray(R.array.data_book_description)
        val dataCover = resources.obtainTypedArray(R.array.data_book_cover)

        val listBook = ArrayList<Book>()

        for (i in dataTitle.indices) {
            val book = Book(
                dataTitle[i],
                dataAuthor[i],
                dataPublisher[i],
                dataReleaseYear[i],
                dataDescription[i],
                dataCover.getResourceId(i, -1))
            listBook.add(book)
        }

        return listBook
    }

    private fun showRecyclerList() {
        rvBooks.layoutManager = LinearLayoutManager(this)
        val listBookAdapter = ListBookAdapter(list)
        rvBooks.adapter = listBookAdapter

        listBookAdapter.setOnItemClickCallback(object : ListBookAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Book) {
                val moveIntent = Intent(this@MainActivity, DetailBookActivity::class.java)
                moveIntent.putExtra(INTENT_PARCELABLE, data)
                startActivity(moveIntent)
            }
        })
    }
}