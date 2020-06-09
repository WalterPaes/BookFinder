package dev.wpaes.bookfinder

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class BookDetailActivity : AppCompatActivity() {

    private var thumbnail: ImageView? = null
    private var title: TextView? = null
    private var description: TextView? = null
    private var author: TextView? = null
    private var category: TextView? = null
    private var language: TextView? = null
    private var publisher: TextView? = null
    private var publishedDate: TextView? = null
    private var pageCount: TextView? = null

    companion object {
        const val THUMBNAIL = "thumbnail"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val AUTHOR = "author"
        const val CATEGORY = "category"
        const val LANGUAGE = "language"
        const val PUBLISHER = "publisher"
        const val PUBLISHED_DATE = "publishedDate"
        const val PAGE_COUNT = "pageCount"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this.thumbnail = findViewById(R.id.imgThumbnail)
        this.title = findViewById(R.id.txtTitle)
        this.description = findViewById(R.id.txtDescription)
        this.author = findViewById(R.id.txtAuthor)
        this.category = findViewById(R.id.txtCategory)
        this.language = findViewById(R.id.txtLanguage)
        this.publisher = findViewById(R.id.txtPublisher)
        this.publishedDate = findViewById(R.id.txtPublishedDate)
        this.pageCount = findViewById(R.id.txtPageCount)

        this.title?.text = intent.getStringExtra(TITLE)
        this.description?.text = intent.getStringExtra(DESCRIPTION)
        this.language?.text =
            getString(R.string.book_language).format(intent.getStringExtra(LANGUAGE))
        this.publisher?.text =
            getString(R.string.book_publisher).format(intent.getStringExtra(PUBLISHER))
        this.publishedDate?.text =
            getString(R.string.book_published_date).format(intent.getStringExtra(PUBLISHED_DATE))
        this.pageCount?.text =
            getString(R.string.book_page_count).format(intent.getIntExtra(PAGE_COUNT, 0))
        this.author?.text = getString(R.string.book_authors).format(intent.getStringExtra(AUTHOR))
        this.category?.text =
            getString(R.string.book_categories).format(intent.getStringExtra(CATEGORY))

        if (intent.getStringExtra(THUMBNAIL).isNullOrEmpty()) {
            this.thumbnail!!.visibility = View.GONE
        }
        Picasso.get().load(intent.getStringExtra(THUMBNAIL)).into(this.thumbnail)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
