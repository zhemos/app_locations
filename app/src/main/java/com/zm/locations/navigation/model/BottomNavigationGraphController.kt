package com.zm.locations.navigation.model

import androidx.navigation.NavController

data class BottomNavigationGraphController(
    val navController: NavController,
    val bottomNavigationGraph: BottomNavigationGraph
) {

    companion object {
        fun build(navController: NavController): BottomNavigationGraphController {
            return BottomNavigationGraphController(
                navController = navController,
                bottomNavigationGraph = BottomNavigationGraph.build(
                    graphId = navController.graph.id
                )
            )
        }
    }
}