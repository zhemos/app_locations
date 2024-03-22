package com.zm.locations.feature.budget

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zm.locations.core.designsystem.components.BaseFragment
import com.zm.locations.core.designsystem.components.Navigation
import com.zm.locations.feature.budget.databinding.FragmentBudgetBinding

interface BudgetNavigation : Navigation

class BudgetFragment : BaseFragment<FragmentBudgetBinding, BudgetNavigation>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBudgetBinding.inflate(inflater, container, false)

    override fun onViewCreated() = Unit
}