package com.zm.locations.feature.board

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zm.locations.core.designsystem.components.BaseFragment
import com.zm.locations.core.designsystem.components.Navigation
import com.zm.locations.feature.board.databinding.FragmentBoardBinding

interface BoardNavigation : Navigation

class BoardFragment : BaseFragment<FragmentBoardBinding, BoardNavigation>() {
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBoardBinding.inflate(inflater, container, false)

    override fun onViewCreated() = Unit
}