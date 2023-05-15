package uk.ac.abertay.planningboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import timber.log.Timber
import uk.ac.abertay.planningboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout

//Sets up the fragment that is used to hold the drawerlayout for navigation throughout the application
        val navController = this.findNavController(R.id.myNavHostFragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        NavigationUI.setupWithNavController(binding.navView, navController)

        }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }




    override fun onStart() {
        super.onStart()
        Timber.i( "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i( "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i( "onPause called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i( "onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i( "onRestart called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i( "onStop called")
    }

    }





