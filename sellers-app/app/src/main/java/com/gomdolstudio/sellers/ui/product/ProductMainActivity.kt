package com.gomdolstudio.sellers.ui.product

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.common.Prefs
import com.gomdolstudio.sellers.databinding.ActivityProductmainBinding
import com.gomdolstudio.sellers.ui.signin.SigninActivity
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ProductMainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    @Inject
    lateinit var binding: ActivityProductmainBinding
    @Inject
    lateinit var viewModelProvider: ViewModelProvider
    private lateinit var productMainViewModel: ProductMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productMainViewModel = viewModelProvider.get(ProductMainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.navView.setNavigationItemSelectedListener(this)
        setupDrawerListener()
    }

    private fun setupDrawerListener(){
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

    companion object {
        private const val MENU_TITLE_INQUERY = "문의"
        private const val MENU_TITLE_LOGOUT = "로그아웃"
    }
}