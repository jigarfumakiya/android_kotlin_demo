package com.app.android_test.features

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.app.android_test.R
import com.app.android_test.core.utility.binding.viewBinding
import com.app.android_test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel: MainActivityViewModel by viewModels()
    private var isReady = false
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { !isReady }
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        checkUserDetails()
    }

    private fun checkUserDetails() {
        lifecycleScope.launch {
            val isLoggedIn = viewModel.userLogged()
            if (isLoggedIn) {
                Log.d("User", "user is logged")
                setupBottomNavigation(R.id.nav_home)
            } else {
                Log.d("User", "user not is logged")
                setupBottomNavigation(R.id.welcomeFragment)
            }
        }


    }


    private fun setupBottomNavigation(destination: Int) {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        val navInflater = navController.navInflater
        val navGraph = navInflater.inflate(R.navigation.nav_graph)
        navGraph.setStartDestination(destination)
        navController.graph = navGraph
        //Add deal to avoid flicker of previous screen
        Handler(Looper.getMainLooper()).postDelayed({ isReady = true }, 800)
        binding.bottomNavigationView.setOnItemReselectedListener {
            // when user click on same item do nothing
        }
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, navHostFragment.navController)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.welcomeFragment -> hideNavigationWithDelay()

                else -> binding.bottomNavigationView.isVisible = true
            }

        }
    }


    private fun hideNavigationWithDelay() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                binding.bottomNavigationView.isVisible = false
            }, 200
        )
    }


}
