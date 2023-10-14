package jt.projects.gbschool.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.girogevoro.schedule.R
import com.girogevoro.schedule.databinding.ItemLessonBinding
import com.girogevoro.schedule.domain.entity.Lesson

class LessonViewHolder private constructor(
    private val binding: ItemLessonBinding
) : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun bind(data: Lesson, onItemClicked: ((Lesson) -> Unit)?) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            with(binding) {

                tvLessonName.text = data.name
                tvTeacher.text = "Teacher: ${data.teacher}"
                tvTime.text = "${data.timeStart} - ${data.timeEnd}"
                ivImage.load(data.image) {
                    error(R.drawable.baseline_home_24)
                }

                if (data.isOpenIn) {
                    btnOpenIn.root.apply {
                        isVisible = true
                        setOnClickListener {
                            onItemClicked?.invoke(data)
                        }
                    }
                }
            }
        }
    }
}