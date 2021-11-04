package com.gomdolstudio.sellers.ui.product
import android.app.Fragment
import com.gomdolstudio.sellers.ui.product.list.ProductListFragment
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle

import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.common.Prefs
import com.gomdolstudio.sellers.databinding.ActivityProductmainBinding
import com.gomdolstudio.sellers.ui.product.registration.ProductRegistrationActivity
import com.gomdolstudio.sellers.ui.signin.SigninActivity
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ProductMainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    @Inject
    lateinit var binding: ActivityProductmainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.navView.setNavigationItemSelectedListener(this)
        setUpDrawerListener()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ProductListFragment())
            .commitNow()

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.title) {
            MENU_TITLE_INQUERY -> { }
            MENU_TITLE_LOGOUT -> {
                Prefs.token = null
                Prefs.refreshToken = null
                startActivity(Intent(this@ProductMainActivity, SigninActivity::class.java))
                finish()
            }
        }
        binding.drawerLayout.closeDrawer(binding.navView)
        return true
    }

    fun setUpDrawerListener(){
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.open_drawer_description,
            R.string.close_drawer_description
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    fun moveToRegistrationActivity(){
        startActivity(Intent(this@ProductMainActivity, ProductRegistrationActivity::class.java))
    }

    companion object {
        private const val MENU_TITLE_INQUERY = "문의"
        private const val MENU_TITLE_LOGOUT = "로그아웃"
    }

}