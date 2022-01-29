package com.todotracks.tdt.src.user_settings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import com.todotracks.tdt.MainActivity
import com.todotracks.tdt.databinding.ActivityUserSettingBinding
import com.todotracks.tdt.kotlin.config.BaseActivity
import com.todotracks.tdt.src.check_map.MapCheckActivity
import com.todotracks.tdt.src.map.MapActivity
import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse
import com.todotracks.tdt.src.user_settings.service.LoginService
import com.todotracks.tdt.src.user_settings.service.LoginView

class UserSettingActivity : BaseActivity<ActivityUserSettingBinding>(ActivityUserSettingBinding::inflate), LoginView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var token_id =  Settings.Secure.getString(
            applicationContext.contentResolver, Settings.Secure.ANDROID_ID
        )
//        showCustomToast(token_id)

        binding.settingOkBtn.setOnClickListener {
            var loginReq = loginRequest(token_id.toString())
            LoginService(this).tryPostLogin(loginReq)
//            my_device_token(token_id)
        }

        binding.settingBtn.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPostLoginSuccess(response: loginResponse) {
        var userId = response.member_no
        my_user_Id(userId)
//        showCustomToast(userId.toString())
//        var intent = Intent(this, MapActivity::class.java)
//        startActivity(intent)
        var intent = Intent(this, MapCheckActivity::class.java)
        startActivity(intent)
    }

    override fun onPostLoginFailure(message: String) {
        TODO("Not yet implemented")
    }
}