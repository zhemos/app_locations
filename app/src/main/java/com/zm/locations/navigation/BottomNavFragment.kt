package com.zm.locations.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationBarView
import com.zm.locations.R
import com.zm.locations.core.designsystem.components.BaseFragment
import com.zm.locations.core.designsystem.components.GenericSavedStateViewModelFactory
import com.zm.locations.core.designsystem.components.Navigation
import com.zm.locations.core.designsystem.ext.disableTooltip
import com.zm.locations.databinding.FragmentBottomNavBinding
import com.zm.locations.feature.location.observer.LocationObservable
import com.zm.locations.navigation.di.BottomNavScreenComponentViewModel
import com.zm.locations.navigation.di.BottomNavScreenDependenciesProvider
import com.zm.locations.navigation.model.BottomNavigationGraph
import com.zm.locations.navigation.model.BottomNavigationGraphController
import javax.inject.Inject

interface BottomNavNavigation : Navigation

interface IBottomNavFragment {

    fun initNavGraphs()

    fun obtainNavHostFragment(graph: BottomNavigationGraph): NavHostFragment

    fun attachNavHostFragment(
        fragment: NavHostFragment,
        isPrimaryNavFragment: Boolean
    )

    fun detachNavHostFragment(fragment: NavHostFragment)

    interface Events {

        fun onChangedController(controller: BottomNavigationGraphController)

        fun onPopBackStack(tagFragment: String)

        fun onAttachFragment(fragment: NavHostFragment, tag: String)

        fun onDetachAllOtherFragments(transaction: FragmentTransaction, tagFragment: String)

        fun isOnBackStack(backStackName: String): Boolean
    }
}

class BottomNavFragment : BaseFragment<FragmentBottomNavBinding, BottomNavNavigation>(),
    IBottomNavFragment, IBottomNavFragment.Events, NavController.OnDestinationChangedListener,
    NavigationBarView.OnItemSelectedListener, FragmentManager.OnBackStackChangedListener {

    private val viewModelComponent: BottomNavScreenComponentViewModel.Impl by viewModels()

    @Inject
    internal lateinit var factory: Factory
    private val viewModel: BottomNavViewModel by viewModels {
        GenericSavedStateViewModelFactory(factory, this)
    }

    @Inject
    internal lateinit var locationObservable: LocationObservable

    private var currentNavController: NavController? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBottomNavBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        val provider = dependenciesProvider<BottomNavScreenDependenciesProvider>()
        viewModelComponent.create(dependenciesProvider = provider, fragment = this)
        super.onAttach(context)
    }

    override fun onViewCreated() = with(binding) {
        bottomNavigationView.menu.findItem(
            viewModel.selectedBottomNavigationGraph.itemId
        ).isChecked = true
        initNavGraphs()
        bottomNavigationView.disableTooltip()
        btnAdd.setOnClickListener { locationObservable.notifyAddNewLocation() }
    }

    override fun onResume() {
        binding.bottomNavigationView.setOnItemSelectedListener(this@BottomNavFragment)
        childFragmentManager.addOnBackStackChangedListener(this@BottomNavFragment)
        super.onResume()
    }

    override fun onStop() {
        binding.bottomNavigationView.setOnItemSelectedListener(null)
        childFragmentManager.removeOnBackStackChangedListener(this@BottomNavFragment)
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.onSaveState()
    }

    override fun initNavGraphs() {
        viewModel.mapGraphs.forEach { (_, graph) ->
            val navHostFragment = obtainNavHostFragment(graph)
            if (viewModel.selectedBottomNavigationGraph.itemId ==
                navHostFragment.navController.graph.id
            ) {
                onChangedController(
                    BottomNavigationGraphController.build(
                        navController = navHostFragment.navController
                    )
                )
                attachNavHostFragment(
                    fragment = navHostFragment,
                    isPrimaryNavFragment = graph.isFirst
                )
            } else {
                detachNavHostFragment(fragment = navHostFragment)
            }
        }
    }

    override fun obtainNavHostFragment(graph: BottomNavigationGraph): NavHostFragment {
        val existingFragment = childFragmentManager
            .findFragmentByTag(graph.tag) as NavHostFragment?
        existingFragment?.let {
            return it
        }
        val navHostFragment = NavHostFragment.create(graph.graphId)
        childFragmentManager.beginTransaction()
            .add(R.id.nav_host_fragment, navHostFragment, graph.tag)
            .commitNow()
        return navHostFragment
    }

    override fun attachNavHostFragment(fragment: NavHostFragment, isPrimaryNavFragment: Boolean) {
        childFragmentManager.beginTransaction()
            .attach(fragment)
            .apply {
                if (isPrimaryNavFragment) {
                    setPrimaryNavigationFragment(fragment)
                }
            }
            .commitNow()
    }

    override fun detachNavHostFragment(fragment: NavHostFragment) {
        childFragmentManager.beginTransaction().detach(fragment).commitNow()
    }

    override fun onChangedController(controller: BottomNavigationGraphController) {
        logger.debug("onChangedController->$controller")
        currentNavController?.removeOnDestinationChangedListener(this)
        currentNavController = controller.navController
        currentNavController?.addOnDestinationChangedListener(this)
    }

    override fun onPopBackStack(tagFragment: String) {
        childFragmentManager.popBackStack(tagFragment, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun onAttachFragment(fragment: NavHostFragment, tag: String) {
        childFragmentManager.beginTransaction().setCustomAnimations(
            androidx.navigation.ui.R.anim.nav_default_enter_anim,
            androidx.navigation.ui.R.anim.nav_default_exit_anim,
            androidx.navigation.ui.R.anim.nav_default_pop_enter_anim,
            androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
        ).attach(fragment).setPrimaryNavigationFragment(fragment).apply {
            onDetachAllOtherFragments(this, tag)
        }.addToBackStack(BottomNavigationGraph.firstBottomNavigationGraph.tag)
            .setReorderingAllowed(true)
            .commit()
    }

    override fun onDetachAllOtherFragments(transaction: FragmentTransaction, tagFragment: String) {
        viewModel.mapGraphs.forEach { entry ->
            if (entry.value.tag != tagFragment) {
                childFragmentManager
                    .findFragmentByTag(BottomNavigationGraph.firstBottomNavigationGraph.tag)
                    ?.let {
                        transaction.detach(it)
                    }
            }
        }
    }

    override fun isOnBackStack(backStackName: String): Boolean {
        val backStackCount = childFragmentManager.backStackEntryCount
        for (index in 0 until backStackCount) {
            if (childFragmentManager.getBackStackEntryAt(index).name == backStackName) {
                return true
            }
        }
        return false
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        logger.debug("onDestinationChanged->$controller, $destination")
        val mainNavController = requireActivity() as NavController.OnDestinationChangedListener
        mainNavController.onDestinationChanged(controller, destination, arguments)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (childFragmentManager.isStateSaved) return false
        val selectedGraph = viewModel.mapGraphs[item.itemId] ?: return false
        if (viewModel.selectedBottomNavigationGraph == selectedGraph) return false
        val selectedFragment = childFragmentManager.findFragmentByTag(selectedGraph.tag)
                as NavHostFragment
        onPopBackStack(BottomNavigationGraph.firstBottomNavigationGraph.tag)
        if (BottomNavigationGraph.firstBottomNavigationGraph.tag != selectedGraph.tag) {
            onAttachFragment(fragment = selectedFragment, tag = selectedGraph.tag)
        }
        viewModel.onSelectBottomNavigationGraph(selectedGraph)
        onChangedController(controller = BottomNavigationGraphController.build(
            navController = selectedFragment.navController
        ))
        return true
    }

    override fun onBackStackChanged() {
        if (
            viewModel.selectedBottomNavigationGraph.isFirst.not() &&
            isOnBackStack(BottomNavigationGraph.firstBottomNavigationGraph.tag).not()
        ) {
            binding.bottomNavigationView.selectedItemId =
                BottomNavigationGraph.firstBottomNavigationGraph.itemId
        }
    }
}