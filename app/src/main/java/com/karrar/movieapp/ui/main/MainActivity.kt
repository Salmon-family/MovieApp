package com.karrar.movieapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
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
    private val viewModel: SharedViewModel by viewModels()

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
        viewModel.toolbarVisibility.observe(this) { visibility ->
            binding.topAppBar.isVisible = visibility
            binding.actionbarTransparent.isVisible = visibility
        }
        viewModel.toolbarTitle.observe(this) { title ->
            binding.topAppBar.title = title
        }

        viewModel.toolbarTransparent.observe(this) { transparent ->
            when (transparent) {
                true -> {
                    binding.topAppBar.background = ContextCompat.getDrawable(this,android.R.color.transparent)
                    binding.actionbarTransparent.isVisible = false
                }
                false ->{
                    binding.topAppBar.background = ContextCompat.getDrawable(this, R.color.background_color)
                }
            }
        }
    }

}