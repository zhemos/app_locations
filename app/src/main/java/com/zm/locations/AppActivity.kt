package com.zm.locations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.zm.locations.core.common.util.Logger
import com.zm.locations.core.designsystem.components.Navigator
import com.zm.locations.core.designsystem.components.NavigatorProvider
import com.zm.locations.navigation.Screen
import javax.inject.Inject

class AppActivity : AppCompatActivity(R.layout.activity_app), NavigatorProvider,
    NavController.OnDestinationChangedListener {

    @Inject
    internal lateinit var logger: Logger

    private var _navigator: Navigator? = null
    override val navigator: Navigator get() = _navigator ?: error("navigator error")

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        navController.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        logger.debug("dest -> ${destination.label}")
        _navigator = Screen.build(
            selfNavController = controller,
            navController = navController,
            destination = destination
        )
    }
}