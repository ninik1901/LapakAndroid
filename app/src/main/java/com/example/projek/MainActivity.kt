package com.example.projek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.projek.fragment.AkunFragment
import com.example.projek.fragment.HomeFragment
import com.example.projek.fragment.NotifFragment
import com.example.projek.fragment.SimpanFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

   private val fragmentHome: Fragment = HomeFragment()
   private val fragmentSimpan: Fragment = SimpanFragment()
   private val fragmentNotif: Fragment = NotifFragment()
   private val fragmentAkun: Fragment = AkunFragment()
   private val fm: FragmentManager = supportFragmentManager
   private var active:Fragment =fragmentHome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNav()

    }

    fun setUpBottomNav(){
        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentSimpan).hide(fragmentSimpan).commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentNotif).hide(fragmentNotif).commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentAkun).hide(fragmentAkun).commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.navigation_home -> {
                    callFragment(0, fragmentHome)
                }
                R.id.navigation_simpan -> {
                    callFragment(1, fragmentSimpan)
                }
                R.id.navigation_notifications -> {
                    callFragment(2, fragmentNotif)
                }
                R.id.navigation_akun -> {
                    callFragment(3, fragmentAkun)
                }
            }

            false
        }

    }

    fun callFragment(int: Int, fragment: Fragment){
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}
