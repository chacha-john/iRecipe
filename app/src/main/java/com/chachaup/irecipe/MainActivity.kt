package com.chachaup.irecipe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.chachaup.irecipe.databinding.ActivityMainBinding
import com.chachaup.irecipe.vm.CookdVM
import com.chachaup.irecipe.vm.CookdVMFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding: ActivityMainBinding

    private val sharedVM: CookdVM by viewModels()

    private lateinit var mLogoutMenuItem: MenuItem

    @Inject lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)

        // controls visibility of the bottom navigation
        sharedVM.bottomNavigationVisibility.observe(this) {
            binding.bottomNavigation.visibility = it
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.meals -> {
                    navController.navigate(R.id.meals)
                    it.isChecked = true
                    true
                }
                R.id.nutritionalTips -> {
                    navController.navigate(R.id.nutritionalTips)
                    it.isChecked = true
                    true
                }
                R.id.favorites -> {
                    navController.navigate(R.id.favorites)
                    it.isChecked = true
                    true
                }
                else -> false
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        if (menu != null) {
            mLogoutMenuItem = menu.findItem(R.id.actionLogout)
        }
        updateLogoutMenuItem()
        return true
    }

    // logging out
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.actionLogout -> {
                if (isUserLoggedIn()){
                    firebaseAuth.signOut()
                } else{
                    findNavController(R.id.nav_host_fragment).navigate(R.id.requestRegistration)
                }
            }
            else -> super.onOptionsItemSelected(item)
        }

        // fixes the bug where implicit navigation doesn't reflect the selected item in the bottom navigation
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    private fun updateLogoutMenuItem(){
        if (isUserLoggedIn()){
            mLogoutMenuItem.title = getString(R.string.text_logout)
        }
        else{
            mLogoutMenuItem.title = getString(R.string.text_login)
        }
    }

    private fun isUserLoggedIn(): Boolean {
        var flag = true
        FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            flag = user == null // is true if there is a user logged in and false when there isn't
    }
        return flag

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }


}