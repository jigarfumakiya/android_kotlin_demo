package com.app.android_test.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.android_test.databinding.ActivityMainBinding
import com.app.android_test.core.utility.binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}