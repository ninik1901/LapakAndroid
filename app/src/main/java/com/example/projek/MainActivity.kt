package com.example.projek

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projek.fragment.AkunFragment
import com.example.projek.fragment.HomeFragment
import com.example.projek.fragment.NotifFragment
import com.example.projek.fragment.SimpanFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private val fragmentHome: Fragment = HomeFragment()
    private val fragmentSimpan: Fragment = SimpanFragment()
    private val fragmentNotif: Fragment = NotifFragment()
    private val fragmentAkun: Fragment = AkunFragment()
    private val fm: FragmentManager = supportFragmentManager
    private var active: Fragment = fragmentHome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_donasiBuku,
                R.id.nav_ebook,
                R.id.nav_profilLapak,
                R.id.nav_jadwal,
                R.id.navigation_notifications,
                R.id.nav_akun,
                R.id.nav_simpan
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
//        NavigationUI.setupActionBarWithNavController(this@MainActivity,navController,appBarConfiguration)
        navView.setupWithNavController(navController)
//        setUpBottomNav()
//        bottomNavigationView = findViewById(R.id.bottom_nav_view)
//        NavigationUI.setupWithNavController(bottomNavigationView,navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_notifications -> {
                Toast.makeText(this@MainActivity, "notif", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

//    fun setUpBottomNav(){
//        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentHome).show(fragmentHome).commit()
////        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentSimpan).hide(fragmentSimpan).commit()
////        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentNotif).hide(fragmentNotif).commit()
////        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentAkun).hide(fragmentAkun).commit()
//
//        bottomNavigationView = findViewById(R.id.bottom_nav_view)
//        menu = bottomNavigationView.menu
//        menuItem = menu.getItem(0)
//        menuItem.isChecked = true
//
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            when(item.itemId){
//                R.id.nav_home -> {
//                    callFragment(0, fragmentHome)
//                }
//                R.id.navigation_simpan -> {
//                    callFragment(1, fragmentSimpan)
//                }
//                R.id.navigation_notifications -> {
//                    callFragment(2, fragmentNotif)
//                }
//                R.id.navigation_akun -> {
//                    callFragment(3, fragmentAkun)
//                }
//            }
//
//            false
//        }
//
//    }
//
//    fun callFragment(int: Int, fragment: Fragment){
//        menuItem = menu.getItem(int)
//        menuItem.isChecked = true
//        fm.beginTransaction().hide(active).show(fragment).commit()
//        active = fragment
//    }
}
