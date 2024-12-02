package com.app.android_test.features

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
        setupBottomNavigation();
        checkUserDetails()
    }

    private fun checkUserDetails() {
        Handler(Looper.getMainLooper()).postDelayed({
            isReady = true
            val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
            lifecycleScope.launch {
                val isLoggedIn = viewModel.userLogged()
                if (isLoggedIn) {
                    navGraph.setStartDestination(R.id.nav_home)
                } else {
                    navGraph.setStartDestination(R.id.welcomeFragment)
                }
            }
        }, 2000)
    }


    private fun setupBottomNavigation() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
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
