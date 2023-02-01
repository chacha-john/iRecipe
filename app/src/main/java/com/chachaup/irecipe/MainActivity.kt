package com.chachaup.irecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.chachaup.irecipe.databinding.ActivityMainBinding
import com.chachaup.irecipe.ui.Meals
import com.chachaup.irecipe.vm.CookdVM
import com.chachaup.irecipe.vm.CookdVMFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val sharedVM: CookdVM by viewModels { CookdVMFactory((application as IRecipeApplication).repo) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfiguration)
        supportActionBar?.hide()

        sharedVM.bottomNavigationVisibility.observe(this, Observer {
            binding.bottomNavigation.visibility = it
        })
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_discover -> {
                    // Handle discover navigation
                    navController.navigate(R.id.meals)
                    it.isChecked = true
                    true
                }
                R.id.nav_tips -> {
                    // Handle tips navigation
                    navController.navigate(R.id.nutritionalTips)
                    it.isChecked = true
                    true
                }
                R.id.nav_favorites -> {
                    // Handle favorites navigation
                    navController.navigate(R.id.favorites)
                    it.isChecked = true
                    true
                }
                else -> false
            }
        }


    }

}