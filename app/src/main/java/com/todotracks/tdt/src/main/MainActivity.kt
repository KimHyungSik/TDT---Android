package com.todotracks.tdt.src.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.todotracks.tdt.R
import com.todotracks.tdt.databinding.ActivityMainBinding
import com.todotracks.tdt.kotlin.config.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()
//
//        binding.mainBtmNav.setOnNavigationItemSelectedListener(
//            BottomNavigationView.OnNavigationItemSelectedListener { item ->
//                when (item.itemId) {
//                    R.id.menu_main_btm_nav_home -> {
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_frm, HomeFragment())
//                            .commitAllowingStateLoss()
//                        return@OnNavigationItemSelectedListener true
//                    }
//                    R.id.menu_main_btm_nav_my_page -> {
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_frm, MyPageFragment())
//                            .commitAllowingStateLoss()
//                        return@OnNavigationItemSelectedListener true
//                    }
//                }
//                false
//            })
    }
}