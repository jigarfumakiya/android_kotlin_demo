package com.app.android_test.features

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.app.android_test.R
import com.app.android_test.core.utility.binding.viewBinding
import com.app.android_test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private var isReady = false

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
            isReady = true;
        }, 2000)
    }


    private fun setupBottomNavigation() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
        binding.bottomNavigationView.setOnItemReselectedListener {
            // when user click on same item do nothing
        }
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, navHostFragment.navController)
        }
    }
}
