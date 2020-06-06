package dev.wpaes.bookfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.wpaes.bookfinder.book.Book

class MainActivity : AppCompatActivity() {
    
    val items: List<Book> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
