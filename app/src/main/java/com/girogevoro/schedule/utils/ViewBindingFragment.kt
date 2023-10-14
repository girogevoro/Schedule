package com.girogevoro.schedule.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
abstract class ViewBindingFragment<VB : ViewBinding>() : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VB>).getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        ).also {
            _binding = it.invoke(null, inflater, container, false) as VB
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}