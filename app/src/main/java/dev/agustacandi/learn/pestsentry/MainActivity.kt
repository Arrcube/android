package dev.agustacandi.learn.pestsentry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dev.agustacandi.learn.pestsentry.databinding.ActivityMainBinding
import dev.agustacandi.learn.pestsentry.utils.ConstVal
import dev.agustacandi.learn.pestsentry.utils.ext.gone
import dev.agustacandi.learn.pestsentry.utils.ext.show

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navHostBottomBar =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navControllerBottomBar = navHostBottomBar.navController

        with(binding) {
            mainBottomNavigation.setupWithNavController(navControllerBottomBar)
            navControllerBottomBar.addOnDestinationChangedListener { _, destination, _ ->
                if (ConstVal.navBarDestination.contains(destination.id)) {
                    mainBottomNavigation.show()
                } else {
                    mainBottomNavigation.gone()
                }
            }
        }
    }
}