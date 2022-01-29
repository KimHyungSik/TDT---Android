package com.todotracks.tdt.src.user_settings

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.todotracks.tdt.R
import com.todotracks.tdt.databinding.ActivityMainBinding
import com.todotracks.tdt.databinding.ActivityUserSettingBinding
import com.todotracks.tdt.kotlin.config.BaseActivity
import com.todotracks.tdt.src.map.MapActivity

class UserSettingActivity : BaseActivity<ActivityUserSettingBinding>(ActivityUserSettingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.settingOkBtn.setOnClickListener {
            var intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }
}