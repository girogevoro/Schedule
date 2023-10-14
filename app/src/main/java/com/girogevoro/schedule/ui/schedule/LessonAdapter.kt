package jt.projects.gbschool.ui.classes

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girogevoro.schedule.domain.entity.Lesson
import jt.projects.gbschool.ui.viewholders.AdditionalLessonViewHolder
import jt.projects.gbschool.ui.viewholders.LessonViewHolder

class LessonAdapter(
    private var onItemClicked: ((Lesson) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val LESSON = 1
        const val ADDITIONAL_LESSON = 2
    }


    private var data: List<Lesson> = arrayListOf()

    fun setData(data: List<Lesson>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position].isAdditional) {
            false -> LESSON
            true -> ADDITIONAL_LESSON
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            LESSON -> LessonViewHolder(parent)
            ADDITIONAL_LESSON -> AdditionalLessonViewHolder(parent)
            else -> throw IllegalStateException()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LessonViewHolder) {
            holder.bind(data[position], onItemClicked)
        }
        if (holder is AdditionalLessonViewHolder) {
            holder.bind(data[position], onItemClicked)
        }
    }

    override fun getItemCount(): Int = data.size
}