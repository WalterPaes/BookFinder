package dev.wpaes.bookfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import com.google.gson.Gson
import dev.wpaes.bookfinder.book.SearchResult
import org.json.JSONObject

class ResultActivity : AppCompatActivity() {

    //private lateinit var listView ListView
    companion object {
        const val RESULT = "result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val txtResult = findViewById<TextView>(R.id.txtResult)

        val result = intent.getStringExtra(RESULT)

        val bookList = Gson().fromJson(result, SearchResult::class.java)
        txtResult.text = bookList.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
