package com.app.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_view_item_book.view.*

class BookAdapter(private var books: List<Book>, private val onItemClick: (book: Book) -> Unit) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindBook(book: Book, onItemClick: (book: Book) -> Unit) {
            itemView.title.text = book.title
            itemView.price.text = book.priceText
            Picasso.get().load(book.cover).into(itemView.imageView)
            itemView.setOnClickListener {
                onItemClick(book)
            }
        }
    }

    override fun getItemCount(): Int = books.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lineView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_view_item_book, parent, false)
        return ViewHolder(lineView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindBook(books[position], onItemClick)
    }

    /**
     * Method to call when we want to update the data of the list
     */
    fun setData(items: List<Book>) {
        books = items
        notifyDataSetChanged()
    }
}
