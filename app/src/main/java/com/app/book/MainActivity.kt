package com.app.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.res.Configuration.ORIENTATION_PORTRAIT

class MainActivity : AppCompatActivity(), ListBookFragment.OnBookClickListener {

    companion object {
        var isPortrait = true
    }

    private val bookDetailFragment: BookDetailFragment = BookDetailFragment()
    private val listBookDetailFragment: ListBookFragment = ListBookFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val orientation = resources.configuration.orientation

        // We show the list of books in portrait
        // We show the list and the details to the right side in landscape
        if (orientation == ORIENTATION_PORTRAIT) {
            isPortrait = true
            setContentView(R.layout.activity_portrait)
            supportFragmentManager.beginTransaction()
                .replace(R.id.portraitFrameLayout, listBookDetailFragment)
                .commit()
        } else {
            isPortrait = false
            setContentView(R.layout.activity_landscape)
            supportFragmentManager.beginTransaction()
                .replace(R.id.leftContainerFrameLayout, listBookDetailFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .replace(R.id.rightContainerFrameLayout, bookDetailFragment)
                .commit()
        }
    }

    /**
     * Action when we click on a book in the list
     */
    override fun onBookClick(book: Book) {
        bookDetailFragment.book = book
        if (isPortrait) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.portraitFrameLayout, bookDetailFragment)
                .addToBackStack("bookDetail")
                .commit()
        } else {
            bookDetailFragment.loadData()
        }
    }

}
