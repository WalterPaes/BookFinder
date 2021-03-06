package dev.wpaes.bookfinder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import dev.wpaes.bookfinder.adapter.BookAdapter
import dev.wpaes.bookfinder.book.SearchResult
import java.util.*

class ResultActivity : AppCompatActivity() {

    private var result: String? = null
    private var txtResult: TextView? = null

    companion object {
        const val RESULT = "result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this.result = intent.getStringExtra(RESULT)
        this.txtResult = findViewById(R.id.txtResult)

        val bookList = Gson().fromJson(this.result, SearchResult::class.java)
        Log.d("BOOKLIST", bookList.toString())

        if (bookList.totalItems > 0) {
            val listView = findViewById<ListView>(R.id.result_list_view)
            val adapter = BookAdapter(this, bookList.items)

            this.txtResult?.text = getString(R.string.result_total_items).format(adapter.count)
            listView.adapter = adapter

            listView.setOnItemClickListener { _, _, position, _ ->
                val item = bookList.items[position]
                val intent = Intent(this, BookDetailActivity::class.java)

                intent.putExtra(BookDetailActivity.THUMBNAIL, item.volumeInfo.imageLinks?.thumbnail)
                intent.putExtra(BookDetailActivity.TITLE, item.volumeInfo.title)
                intent.putExtra(BookDetailActivity.DESCRIPTION, item.volumeInfo.description)
                intent.putExtra(BookDetailActivity.LANGUAGE, item.volumeInfo.language.toUpperCase(
                    Locale.ROOT))
                intent.putExtra(BookDetailActivity.PUBLISHER, item.volumeInfo.publisher)
                intent.putExtra(BookDetailActivity.PUBLISHED_DATE, item.volumeInfo.publishedDate)
                intent.putExtra(BookDetailActivity.PAGE_COUNT, item.volumeInfo.pageCount)
                intent.putExtra(BookDetailActivity.AUTHOR, item.volumeInfo.authors?.joinToString())
                intent.putExtra(BookDetailActivity.CATEGORY, item.volumeInfo.categories?.joinToString())

                startActivity(intent)
            }
            return
        }

        this.txtResult?.text = getString(R.string.result_total_items).format(bookList.totalItems)
        Toast.makeText(
            this, "Zero results on Search!",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
