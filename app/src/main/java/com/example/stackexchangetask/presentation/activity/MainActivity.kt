package com.example.stackexchangetask.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.stackexchangetask.R
import com.example.stackexchangetask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_search)

        val navController = findNavController(R.id.fragment_container_view)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.search.setOnClickListener {
            navController.navigate(R.id.action_HomeFragment_to_SearchFragment)
        }

//        viewModel.searchQuestions("stdout", "java;go;python")

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_container_view)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}