package com.e.testappmovies.ui.movies.adapter

import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.testappmovies.R
import com.e.testappmovies.data.model.Results
import kotlinx.android.synthetic.main.rc_view_item_movies.view.*
import java.util.Collections.addAll

class MoviesAdapter (
    private var listArray: ArrayList<Results>,
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.rc_view_item_movies, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listArray[position])

    override fun getItemCount() = listArray.size

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(listItem: Results) {
            view.apply {
                textViewTitle.text = listItem.title
                textViewDescription.text = listItem.description
                Glide.with(this)
                    .load(listItem.multimedia.image)
                    .error(R.drawable.error_photo)
                    .dontAnimate()
                    .into(imageViewSrc)
            }
        }
    }
    fun addMovies(movies: List<Results>) {
        listArray.apply {
            clear()
            addAll(movies)
            notifyDataSetChanged()
        }

    }
}