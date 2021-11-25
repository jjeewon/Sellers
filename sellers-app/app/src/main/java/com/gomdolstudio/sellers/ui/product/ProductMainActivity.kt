package com.gomdolstudio.sellers.ui.product
import android.app.Fragment
import com.gomdolstudio.sellers.ui.product.list.ProductListFragment
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.gomdolstudio.sellers.Config

import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.common.Prefs
import com.gomdolstudio.sellers.databinding.ActivityProductmainBinding
import com.gomdolstudio.sellers.databinding.NaviHeaderBinding
import com.gomdolstudio.sellers.ui.product.detail.ProductDetailActivity
import com.gomdolstudio.sellers.ui.product.registration.ProductRegistrationActivity
import com.gomdolstudio.sellers.ui.signin.SigninActivity
import com.google.android.material.navigation.NavigationBarMenu
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.navi_header.*
import kotlinx.android.synthetic.main.navi_header.view.*
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

        val iv: ImageView = binding.navView.getHeaderView(0).findViewById(R.id.header_iv)
        Glide.with(this)
            .load(R.drawable.user)
            .transition(withCrossFade(500))
            .apply(RequestOptions.circleCropTransform())
            .thumbnail(0.5f)
            .into(iv)

    }

    fun moveToRegistrationActivity(){
        startActivity(Intent(this@ProductMainActivity, ProductRegistrationActivity::class.java))
    }

    fun moveToProductDetatilActivity(itemId: Long){
        val intent = Intent(this@ProductMainActivity, ProductDetailActivity::class.java)
        intent.apply {
            putExtra(Config.ITEM_ID, itemId)
            startActivity(this)
        }
    }

    companion object {
        private const val MENU_TITLE_INQUERY = "문의"
        private const val MENU_TITLE_LOGOUT = "로그아웃"
    }

}