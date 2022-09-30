package com.example.myapplication

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * to setup recyclerview with adapter and layout manager
 */
fun <VH : RecyclerView.ViewHolder?> RecyclerView.setUpRecyclerView(
    adapter: RecyclerView.Adapter<VH>, layoutManager: RecyclerView.LayoutManager
) {
    this.adapter = adapter
    this.layoutManager = layoutManager
}


/**
 * setUpInitialPaginationLoader will only apply on PagingDataAdapter
 * this function will show initial loader of your screen when load data from remote or local database
 */

fun <T : Any, VH : RecyclerView.ViewHolder> PagingDataAdapter<T, VH>.setUpInitialPaginationLoader(
    view: View, context: Context
) {
    // show the loading state for te first load
    this.addLoadStateListener { loadState ->
        if (loadState.refresh is LoadState.Loading) {
            // Show view
            view.visibility = View.VISIBLE
        } else {
            // Hide View
            view.visibility = View.GONE


            // If we have an error, show a toast
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> {
                    Toast.makeText(
                        context, "Please check your internet and try again", Toast.LENGTH_LONG
                    ).show()
                    loadState.refresh as LoadState.Error
                }
                else -> null
            }
            errorState?.let {
                if (it.error.message.isNullOrBlank().not()) {
                    Toast.makeText(context, it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}


