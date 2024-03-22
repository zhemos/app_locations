package com.zm.locations.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.zm.locations.R
import com.zm.locations.core.designsystem.components.Navigator
import com.zm.locations.feature.board.BoardNavigation
import com.zm.locations.feature.budget.BudgetNavigation
import com.zm.locations.feature.location.LocationNavigation
import com.zm.locations.feature.settings.SettingsNavigation

abstract class Screen(
    private val navController: NavController
) : Navigator {

    override fun navigate(actionId: Int) {
        navController.navigate(actionId)
    }

    override fun navigateWithData(actionId: Int, data: Bundle) {
        navController.navigate(actionId, data)
    }

    override fun navigateBack() {
        navController.popBackStack()
    }

    companion object {

        fun build(
            selfNavController: NavController,
            navController: NavController,
            destination: NavDestination,
        ): Screen {
            return when (destination.id) {
                R.id.bottom_nav -> {
                    BottomNavScreen(selfNavController)
                }
                R.id.settings -> {
                    SettingsScreen(selfNavController, navController)
                }
                R.id.budget -> {
                    BudgetScreen(selfNavController, navController)
                }
                R.id.board -> {
                    BoardScreen(selfNavController, navController)
                }
                R.id.location -> {
                    LocationScreen(selfNavController, navController)
                }
                else -> error("${destination.label} is absent")
            }
        }
    }
}

interface TabNavigator {

    fun selfNavigate(actionId: Int)

    fun selfNavigateWithData(actionId: Int, data: Bundle)

    fun selfNavigateBack()
}

abstract class TabScreen(
    private val selfNavController: NavController,
    navController: NavController
) : Screen(navController), TabNavigator {

    abstract val isRoot: Boolean

    override fun selfNavigate(actionId: Int) {
        selfNavController.navigate(actionId)
    }

    override fun selfNavigateWithData(actionId: Int, data: Bundle) {
        selfNavController.navigate(actionId, data)
    }

    override fun selfNavigateBack() {
        selfNavController.popBackStack()
    }
}

class BottomNavScreen(navController: NavController) : Screen(navController), BottomNavNavigation

class BudgetScreen(
    selfNavController: NavController,
    navController: NavController,
) : TabScreen(selfNavController, navController), BudgetNavigation {
    override val isRoot: Boolean get() = true
}

class SettingsScreen(
    selfNavController: NavController,
    navController: NavController,
) : TabScreen(selfNavController, navController), SettingsNavigation {
    override val isRoot: Boolean get() = true
}

class BoardScreen(
    selfNavController: NavController,
    navController: NavController,
) : TabScreen(selfNavController, navController), BoardNavigation {
    override val isRoot: Boolean get() = true
}

class LocationScreen(
    selfNavController: NavController,
    navController: NavController,
) : TabScreen(selfNavController, navController), LocationNavigation {
    override val isRoot: Boolean get() = true
}