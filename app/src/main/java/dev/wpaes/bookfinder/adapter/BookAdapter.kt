package dev.wpaes.bookfinder.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import dev.wpaes.bookfinder.R
import dev.wpaes.bookfinder.book.Book
import dev.wpaes.bookfinder.book.VolumeInfo

class BookAdapter(
    context: Context,
    private val dataSource: List<Book>
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item_book, parent, false)

        val titleTextView = rowView.findViewById<TextView>(R.id.book_list_title)
        val publishedDateTextView = rowView.findViewById<TextView>(R.id.book_list_subtitle)
        val thumbnailImageView = rowView.findViewById<ImageView>(R.id.book_list_thumbnail)

        val book = getItem(position) as Book
        titleTextView.text = book.volumeInfo.title
        publishedDateTextView.text = book.volumeInfo.publishedDate
        Picasso.get().load(book.volumeInfo.imageLinks.smallThumbnail).into(thumbnailImageView)
        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}