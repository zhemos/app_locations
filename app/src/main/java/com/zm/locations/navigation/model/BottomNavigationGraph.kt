package com.zm.locations.navigation.model

import com.zm.locations.R

sealed class BottomNavigationGraph {

    abstract val graphId: Int
    abstract val itemId: Int
    abstract val tag: String

    val isFirst: Boolean get() = this == firstBottomNavigationGraph

    data object Settings : BottomNavigationGraph() {
        override val graphId: Int get() = R.navigation.nav_graph_settings
        override val itemId: Int get() = R.id.nav_graph_settings
        override val tag: String get() = "BottomNavigationGraph${Settings::class.java.simpleName}"
    }

    data object Budget : BottomNavigationGraph() {
        override val graphId: Int get() = R.navigation.nav_graph_budget
        override val itemId: Int get() = R.id.nav_graph_budget
        override val tag: String get() = "BottomNavigationGraph${Budget::class.java.simpleName}"
    }

    data object Board : BottomNavigationGraph() {
        override val graphId: Int get() = R.navigation.nav_graph_board
        override val itemId: Int get() = R.id.nav_graph_board
        override val tag: String get() = "BottomNavigationGraph${Board::class.java.simpleName}"
    }

    data object Location : BottomNavigationGraph() {
        override val graphId: Int get() = R.navigation.nav_graph_location
        override val itemId: Int get() = R.id.nav_graph_location
        override val tag: String get() = "BottomNavigationGraph${Location::class.java.simpleName}"
    }

    companion object {
        val firstBottomNavigationGraph: BottomNavigationGraph = Location

        fun build(graphId: Int = 0, itemId: Int = 0): BottomNavigationGraph {
            return when (graphId or itemId) {
                Settings.itemId -> Settings
                Budget.itemId -> Budget
                Board.itemId -> Board
                Location.itemId -> Location
                else -> error("$graphId BottomNavigationGraph error")
            }
        }

        fun getMapAllGraphs(): Map<Int, BottomNavigationGraph> {
            return mapOf(
                Settings.itemId to Settings,
                Budget.itemId to Budget,
                Board.itemId to Board,
                Location.itemId to Location,
            )
        }
    }
}