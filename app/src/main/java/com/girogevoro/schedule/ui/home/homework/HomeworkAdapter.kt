package com.girogevoro.schedule.ui.home.homework

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girogevoro.schedule.domain.entity.Homework

class HomeworkAdapter() : RecyclerView.Adapter<HomeworkViewHolder>() {

    private var data: List<Homework> = arrayListOf()

    fun setData(data: List<Homework>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeworkViewHolder(parent)

    override fun onBindViewHolder(holder: HomeworkViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}