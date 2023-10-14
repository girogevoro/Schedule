package com.girogevoro.schedule.ui.home.homework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.girogevoro.schedule.R
import com.girogevoro.schedule.databinding.ItemHomeworkBinding
import com.girogevoro.schedule.domain.entity.Homework

class HomeworkViewHolder private constructor(
    private val binding: ItemHomeworkBinding,
) : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemHomeworkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    fun bind(data: Homework) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            with(binding) {
                tvLessonName.text = data.name
                tvDescription.text = data.description
                tvTime.text = "${data.daysLeft} days left"
                ivImage.load(data.image) {
                    error(R.drawable.baseline_home_24)
                }

                if (data.daysLeft < 3) {
                    binding.tvTime.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.red
                        )
                    )
                    binding.ivClock.setColorFilter(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.red
                        ), android.graphics.PorterDuff.Mode.SRC_IN
                    )
                }
            }
        }
    }
}