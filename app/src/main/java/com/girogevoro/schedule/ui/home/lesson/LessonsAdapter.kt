package com.girogevoro.schedule.ui.home.lesson

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girogevoro.schedule.domain.entity.Lesson

class LessonsAdapter(
) : RecyclerView.Adapter<LessonHomeViewHolder>() {

    private var data: List<Lesson> = arrayListOf()

    fun setData(data: List<Lesson>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LessonHomeViewHolder(parent)

    override fun onBindViewHolder(holder: LessonHomeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}