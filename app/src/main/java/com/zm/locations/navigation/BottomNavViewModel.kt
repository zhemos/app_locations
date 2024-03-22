package com.zm.locations.navigation

import androidx.lifecycle.SavedStateHandle
import com.zm.locations.core.common.util.Logger
import com.zm.locations.core.designsystem.components.BaseViewModel
import com.zm.locations.core.designsystem.components.ViewModelAssistedFactory
import com.zm.locations.navigation.model.BottomNavigationGraph
import javax.inject.Inject

interface IBottomNavViewModel {

    interface Events {

        fun onSelectBottomNavigationGraph(graph: BottomNavigationGraph)

        fun onSaveState()
    }
}

class BottomNavViewModel(
    savedStateHandle: SavedStateHandle,
    logger: Logger,
) : BaseViewModel(savedStateHandle, logger), IBottomNavViewModel.Events {

    companion object {
        private const val KEY_BOTTOM_NAVIGATION_ITEM_ID = "bottomNavigationItemId"
    }

    val mapGraphs = BottomNavigationGraph.getMapAllGraphs()

    private var _selectedBottomNavigationGraph: BottomNavigationGraph
    val selectedBottomNavigationGraph get() = _selectedBottomNavigationGraph

    init {
        val currentItemId = savedStateHandle.get<Int>(KEY_BOTTOM_NAVIGATION_ITEM_ID)
        _selectedBottomNavigationGraph = currentItemId?.let {
            BottomNavigationGraph.build(itemId = it)
        } ?: BottomNavigationGraph.firstBottomNavigationGraph
    }

    override fun onSelectBottomNavigationGraph(graph: BottomNavigationGraph) {
        _selectedBottomNavigationGraph = graph
    }

    override fun onSaveState() {
        savedStateHandle[KEY_BOTTOM_NAVIGATION_ITEM_ID] = selectedBottomNavigationGraph.itemId
    }
}

class Factory @Inject constructor(
    private val logger: Logger
) : ViewModelAssistedFactory<BottomNavViewModel> {

    override fun create(savedStateHandle: SavedStateHandle): BottomNavViewModel {
        return BottomNavViewModel(
            savedStateHandle = savedStateHandle,
            logger = logger
        )
    }
}