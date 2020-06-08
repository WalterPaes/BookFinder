package dev.wpaes.bookfinder

import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import dev.wpaes.bookfinder.adapter.BookAdapter
import dev.wpaes.bookfinder.book.SearchResult

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

        val listView = findViewById<ListView>(R.id.result_list_view)
        val adapter = BookAdapter(this, bookList.items)

        this.txtResult?.text = getString(R.string.result_total_items).format(adapter.count)

        listView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
