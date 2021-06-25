package com.e.testappmovies.ui.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.testappmovies.R
import kotlinx.android.synthetic.main.rc_view_item_movies.view.*

class MoviesAdapter (
    private var listArray: ArrayList<ItemMovies>,
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.rc_view_item_movies, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listArray[position])

    override fun getItemCount() = listArray.size

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(listItem: ItemMovies) {
            view.apply {
                textViewTitle.text = listItem.title
                textViewDescription.text = listItem.description
                Glide.with(this)
                    .load(listItem.image)
                    .error(R.drawable.error_photo)
                    .dontAnimate()
                    .into(imageViewSrc)
            }
        }
    }
    fun addMovies(movies: List<ItemMovies>) {
        listArray.apply {
            clear()
            addAll(movies)
            notifyDataSetChanged()
        }

    }
}