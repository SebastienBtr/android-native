package com.app.book

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.book.MainActivity.Companion.isPortrait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListBookFragment : Fragment() {

    private lateinit var listener: OnBookClickListener
    private lateinit var bookAdapter: BookAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnBookClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadBooks()
        val view = inflater.inflate(R.layout.fragment_list_book, container, false)
        val recycleListView = view.findViewById<RecyclerView>(R.id.booksRecyclerView)
        recycleListView.layoutManager = LinearLayoutManager(context)
        bookAdapter = BookAdapter(emptyList(), listener::onBookClick)
        recycleListView.adapter = bookAdapter

        return view
    }

    /**
     * Load the remote books data
     */
    private fun loadBooks() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://henri-potier.xebia.fr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(HenriPotierService::class.java)

        val booksRequest = service.getBooks()

        booksRequest.enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                val books = response.body()
                if (books != null && books.isNotEmpty()) {
                    bookAdapter.setData(books)
                    if (!isPortrait) {
                        listener.onBookClick(books[0])
                    }
                }
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                //TODO show a message in the screen or a toast
                Log.i("loadBooks", "fail")
            }
        })
    }

    interface OnBookClickListener {
        /**
         * Action when we click on a book in the list
         */
        fun onBookClick(book: Book)
    }
}
