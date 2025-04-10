package com.example.cooptesapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cooptesapp.base.BaseUiActions

abstract class BaseFragment(layoutResId: Int) : Fragment(layoutResId) {

    lateinit var baseUiActions: BaseUiActions

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseUiActions = (activity as BaseUiActions)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun clearBinding()

    override fun onDestroyView() {
        super.onDestroyView()
        clearBinding()
    }

}