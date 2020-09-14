package com.prince.foodbreak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class OpenActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    var previousMenuItem:MenuItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigationView)
        setUpToolbar()
        openHome()

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@OpenActivity,drawerLayout,R.string.open_drawer,R.string.close_drawer)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            if(previousMenuItem != null){
                previousMenuItem?.isChecked= false

            }
            it.isCheckable = true
            it.isChecked = true
             previousMenuItem = it

            when(it.itemId){
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame,HomeFragment()).commit()
                    supportActionBar?.title = "All Restaurants"
                    drawerLayout.closeDrawers()

                }
                R.id.my_profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame,MyProfileFragment()).commit()
                    supportActionBar?.title = "My profile"
                    drawerLayout.closeDrawers()

                }
                R.id.favorite -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame,FavoriteFragment()).commit()
                    supportActionBar?.title = "Favorite Restaurants"
                    drawerLayout.closeDrawers()

                }
                R.id.order_history -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame,OrderHistoryFragment()).commit()
                    supportActionBar?.title = "My Previous Orders"
                    drawerLayout.closeDrawers()

                }
                R.id.faq -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame,FaqFragment()).commit()
                    supportActionBar?.title = "Frequently Asked Questions"
                    drawerLayout.closeDrawers()

                }
                R.id.log_out -> {


                }


            }

            return@setNavigationItemSelectedListener true
        }

    }

    fun setUpToolbar(){
       setSupportActionBar(toolbar)
        supportActionBar?.title = "Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id ==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)

    }
    fun openHome(){
        val fragment = HomeFragment()
        val transction = supportFragmentManager.beginTransaction()
        transction.replace(R.id.frame,fragment)
        transction.commit()
        supportActionBar?.title = "All Restaurants"
        navigationView.setCheckedItem(R.id.home)

    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frame)
        when(frag){
            !is HomeFragment -> openHome()
            else -> super.onBackPressed()

        }
    }



}
