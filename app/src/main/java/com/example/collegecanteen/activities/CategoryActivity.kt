package com.example.collegecanteen.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.example.collegecanteen.R
import android.content.Intent
import android.view.MenuItem
//import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
//import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class CategoryActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        auth= FirebaseAuth.getInstance()
        val currentUser=auth.currentUser

        if(currentUser==null){
            startActivity(Intent(this,PhoneAuth::class.java))
            finish()
        }

//        val varLogOut = findViewById<View>(R.id.nav_logout) as TextView
//        varLogOut.setOnClickListener {
//            val intent = Intent(this@CategoryActivity, PhoneAuth::class.java)
//            startActivity(intent)
//        }

        fun onNavigationItemSelected(menuItem: MenuItem):Boolean{
            when(menuItem.itemId){
                R.id.nav_logout ->{
                    val intent = Intent(this@CategoryActivity, PhoneAuth::class.java)
                    startActivity(intent)
                }
            }
            return true
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.nav_home -> Toast.makeText(applicationContext, "Clicked Home",
                    Toast.LENGTH_SHORT
                )
                    .show()
                R.id.order -> Toast.makeText(applicationContext, "Clicked Order",
                    Toast.LENGTH_SHORT
                )
                    .show()
                R.id.setting -> Toast.makeText(applicationContext, "Clicked setting",
                    Toast.LENGTH_SHORT
                )
                    .show()
                R.id.nav_logout -> Toast.makeText(applicationContext, "Clicked Logout",
                    Toast.LENGTH_SHORT
                )
                    .show()
                R.id.nav_share -> Toast.makeText(applicationContext, "Clicked Share",
                    Toast.LENGTH_SHORT
                )
                    .show()
                R.id.nav_rate_us -> Toast.makeText(
                    applicationContext,
                    "Clicked Rate Us",
                    Toast.LENGTH_SHORT
                ).show()
            }
            true
        }

        val varFastFood = findViewById<View>(R.id.fastFood) as LinearLayout
        varFastFood.setOnClickListener {
            val intent = Intent(this@CategoryActivity, FastFoodProductActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    }