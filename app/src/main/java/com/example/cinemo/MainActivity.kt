package com.example.cinemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.cinemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolBar()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite_button -> {
                val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
                navController.navigate(R.id.favoriteMovieListFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun toggleMenuVisibility(menu: Menu, itemId: Int, isVisible: Boolean) {
        val item = menu.findItem(itemId)
        item?.isVisible = isVisible
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        val currentDestinationId = navController.currentDestination?.id

        // Toggle visibility of favorite button based on the current destination
        val isFavoriteButtonVisible = currentDestinationId != R.id.movieDetailFragment
        toggleMenuVisibility(menu, R.id.action_favorite_button, isFavoriteButtonVisible)

        return super.onPrepareOptionsMenu(menu)
    }

    private fun setToolBar(){
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }


}