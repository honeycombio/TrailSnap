package com.honeycomb.trailsnap.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.honeycomb.trailsnap.R
import com.honeycomb.trailsnap.model.Trail

/**
 * Adapter for displaying trails in a RecyclerView.
 */
class TrailAdapter : RecyclerView.Adapter<TrailAdapter.TrailViewHolder>() {

    private var trails: List<Trail> = emptyList()

    /**
     * Updates the list of trails and refreshes the RecyclerView.
     */
    fun submitList(newTrails: List<Trail>) {
        trails = newTrails
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trail, parent, false)
        return TrailViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrailViewHolder, position: Int) {
        holder.bind(trails[position])
    }

    override fun getItemCount(): Int = trails.size

    /**
     * ViewHolder for a single trail item.
     */
    class TrailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.trail_name)
        private val difficultyTextView: TextView = itemView.findViewById(R.id.trail_difficulty)

        fun bind(trail: Trail) {
            nameTextView.text = trail.name
            difficultyTextView.text = trail.difficulty
        }
    }
}
