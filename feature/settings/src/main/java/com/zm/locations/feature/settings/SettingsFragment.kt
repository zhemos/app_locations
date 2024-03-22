package com.zm.locations.feature.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zm.locations.core.designsystem.components.BaseFragment
import com.zm.locations.core.designsystem.components.Navigation
import com.zm.locations.feature.settings.databinding.FragmentSettingsBinding

interface SettingsNavigation : Navigation

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsNavigation>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSettingsBinding.inflate(inflater, container, false)

    override fun onViewCreated() = Unit
}