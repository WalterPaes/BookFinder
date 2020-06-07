package dev.wpaes.bookfinder

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import dev.wpaes.bookfinder.book.Book

class MainActivity : AppCompatActivity() {

    private var txtSearch: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.txtSearch = findViewById(R.id.txtSearch)
        val btnSearch = findViewById<Button>(R.id.btnSearch)

        btnSearch.setOnClickListener {
            val search = this.txtSearch?.text.toString()
            if (search.isEmpty()) {
                Toast.makeText(
                    this, "Type a author's name or a book's name in search field",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val queue = Volley.newRequestQueue(this)
            val url = String.format(
                "https://www.googleapis.com/books/v1/volumes?q=%s:keyes&key=%s",
                search,
                getString(R.string.google_api_key)
            )

            val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener { response ->
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra(ResultActivity.RESULT, response)
                    startActivity(intent)
                },
                Response.ErrorListener {
                    Toast.makeText(
                        this, "That didn't work!",
                        Toast.LENGTH_SHORT
                    ).show()
                })

            queue.add(stringRequest)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putCharSequence("SEARCH", this.txtSearch?.text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        this.txtSearch?.text = savedInstanceState.getCharSequence("SEARCH") as Editable?
    }
}
