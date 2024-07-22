package com.adso.myapplicationsantes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.adso.myapplicationsantes.databinding.ActivityHomeBinding
import com.adso.myapplicationsantes.network.Retrofitclient
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
        private lateinit var fragmentManager: FragmentManager
        private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> openFragment(Home2Fragment())
                R.id.bottom_profile -> openFragment(ProfileFragment())
                R.id.bottom_chat -> openFragment(ChatFragment())
                R.id.bottom_notification -> openFragment(NotificationFragment())
            }
            true
        }

        fragmentManager = supportFragmentManager
        openFragment(Home2Fragment())


        binding.fab.setOnClickListener {
            val popup = PopupMenu(this, it)
            menuInflater.inflate(R.menu.publication_menu, popup.menu)

            popup.show()
            popup.setOnMenuItemClickListener(::manageItemClick)
        }

    }

    private fun manageItemClick(menuItem: MenuItem): Boolean {
        return when (menuItem?.itemId) {
            R.id.ajustes -> {
                Toast.makeText(this, "copiar", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.opc1 -> {
                Toast.makeText(this, "copiar", Toast.LENGTH_SHORT).show()
                true
            }

            else -> false
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_opc1 -> openFragment(Opc1Fragment())
            R.id.nav_opc2 -> openFragment(Opc2Fragment())
            R.id.nav_opc3 -> openFragment(Opc3Fragment())
            R.id.nav_opc4 -> openFragment(Opc4Fragment())

            R.id.logaut -> {
                Retrofitclient.apiservice.logout().enqueue(object :Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if(response.isSuccessful){
                            val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
                            sharedPreferences.edit().remove("token").apply()

                            val intent = Intent(this@Home, Ingreso::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this@Home, "Logout failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        // Maneja el error de red
                        Toast.makeText(this@Home, "Network error", Toast.LENGTH_SHORT).show()
                    }
                })

            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
        }
        super.onBackPressed()

    }

    @SuppressLint("SuspiciousIndentation")
    private  fun openFragment(fragment: Fragment){
        val   fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container,fragment)
        fragmentTransaction.commit()
    }

}