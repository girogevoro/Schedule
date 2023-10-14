package com.girogevoro.schedule.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.girogevoro.schedule.R
import com.girogevoro.schedule.databinding.ItemLessonAtHomeFragmentBinding
import com.girogevoro.schedule.domain.entity.Lesson

class LessonHomeViewHolder private constructor(
    private val binding: ItemLessonAtHomeFragmentBinding
) : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemLessonAtHomeFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun bind(
        data: Lesson
    ) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            with(binding) {

                tvLessonName.text = data.name
                tvTime.text = "${data.timeStart} - ${data.timeEnd}"
                ivImage.load(data.image) {
                    error(R.drawable.baseline_home_24)
                }

            }
        }
    }
}