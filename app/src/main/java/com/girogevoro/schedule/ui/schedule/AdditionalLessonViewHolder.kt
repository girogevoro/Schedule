package jt.projects.gbschool.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.girogevoro.schedule.R
import com.girogevoro.schedule.databinding.ItemLessonAdditionalBinding
import com.girogevoro.schedule.domain.entity.Lesson

class AdditionalLessonViewHolder private constructor(
    private val binding: ItemLessonAdditionalBinding
) : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemLessonAdditionalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun bind(data: Lesson, onItemClicked: ((Lesson) -> Unit)?) {

        if (layoutPosition != RecyclerView.NO_POSITION) {
            with(binding) {

                tvLessonName.text = data.name
                tvTeacher.text = "Teacher: ${data.teacher}"
                tvTime.text = "${data.timeStart} - ${data.timeEnd}"
                tvDescription.text = data.description

                ivImage.load(data.image) {
                    error(R.drawable.baseline_home_24)
                }

            }
        }
    }
}