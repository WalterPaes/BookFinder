package dev.wpaes.bookfinder

import android.os.Bundle
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

    val totalItems: Int = 0
    val items: List<Book> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtSearch = findViewById<EditText>(R.id.txtSearch)
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val btnSearch = findViewById<Button>(R.id.btnSearch)

        btnSearch.setOnClickListener {
            val search = txtSearch.text.toString()
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
                    txtResult.text = "Response is: ${response}"
                },
                Response.ErrorListener { txtResult.text = "That didn't work!" })

            queue.add(stringRequest)
        }
    }
}
