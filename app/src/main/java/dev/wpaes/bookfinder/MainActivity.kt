package dev.wpaes.bookfinder

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.*
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
        val progressBar = findViewById<ProgressBar>(R.id.progress_circular)

        btnSearch.setOnClickListener {
            val search = this.txtSearch?.text.toString()
            if (search.isEmpty()) {
                Toast.makeText(
                    this, "Type a author's name or a book's name in search field",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            progressBar.visibility = View.VISIBLE

            val queue = Volley.newRequestQueue(this)
            val url = String.format(
                "https://www.googleapis.com/books/v1/volumes?q=%s&key=%s",
                search,
                getString(R.string.google_api_key)
            )

            try {
                val stringRequest = StringRequest(Request.Method.GET, url,
                    Response.Listener { response ->
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(ResultActivity.RESULT, response)
                        progressBar.visibility = View.GONE
                        startActivity(intent)
                    },
                    Response.ErrorListener {
                        progressBar.visibility = View.GONE
                        Toast.makeText(
                            this, "That didn't work!",
                            Toast.LENGTH_SHORT
                        ).show()
                    })

                queue.add(stringRequest)
            } catch (ex: Exception) {
                Log.e("STRING_REQUEST", ex.message!!)
            }
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
