package com.karrar.movieapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private val sharedViewModel by lazy { ViewModelProvider(this).get(SharedViewModel::class.java) }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.searchFragment,
                R.id.myListFragment,
                R.id.profileFragment
            )
        )
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupWithNavController(
            binding.topAppBar,
            navHostFragment.navController,
            appBarConfiguration
        )
        setBottomNavBar()
        observeToolbar()
//        setToolBarVisibility()
    }

    private fun setBottomNavBar() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, navController)
            navController.popBackStack(item.itemId, inclusive = false)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun observeToolbar() {
        sharedViewModel.toolbarVisibility.observe(this) { visibility ->
            binding.topAppBar.isVisible = visibility
        }
        sharedViewModel.toolbarTitle.observe(this) { title ->
            binding.topAppBar.title = title

        }
    }

    private fun setToolBarVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.searchFragment,
                R.id.homeFragment,
                R.id.loginFragment
                -> {
                    binding.topAppBar.visibility = View.GONE
                }
                else -> {
                    binding.topAppBar.visibility = View.VISIBLE
                }
            }
        }
    }
}