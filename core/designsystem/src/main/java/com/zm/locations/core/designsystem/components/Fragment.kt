package com.zm.locations.core.designsystem.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.zm.locations.core.common.util.Logger
import com.zm.locations.core.common.util.LoggerProvider

abstract class BaseFragment<B : ViewBinding, N : Navigation> : Fragment() {

    abstract fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): B

    abstract fun onViewCreated()

    private var _binding: B? = null
    val binding: B get() = _binding ?: error("binding error")

    private var _navigation: N? = null
    val navigation: N get() = _navigation ?: error("navigation error")

    protected val logger: Logger by lazy {
        (requireActivity().application as LoggerProvider).logger
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewCreated()
    }

    override fun onStart() {
        super.onStart()
        //navigator init
    }

    override fun onStop() {
        _navigation = null
        super.onStop()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    protected fun <T> dependenciesProvider(): T {
        @Suppress("UNCHECKED_CAST")
        return (requireActivity().application as T)
    }

    protected fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(messageId: Int) {
        Toast.makeText(requireContext(), getString(messageId), Toast.LENGTH_SHORT).show()
    }
}