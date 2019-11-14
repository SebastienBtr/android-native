package com.app.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class BookDetailFragment : Fragment() {

    var book: Book? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    /**
     * Show the book data into the view
     */
    fun loadData() {
        if (book != null) {
            view?.findViewById<TextView>(R.id.detailTitle)?.text = book?.title
            view?.findViewById<TextView>(R.id.detailPrice)?.text = book?.priceText
            val imageView = view?.findViewById<ImageView>(R.id.detailCover)
            Picasso.get().load(book?.cover).into(imageView)
            var synopsis = ""
            book?.synopsis?.forEach {
                synopsis += it + "\n\n"
            }
            view?.findViewById<TextView>(R.id.detailSynopsis)?.text = synopsis
        }
    }
}
