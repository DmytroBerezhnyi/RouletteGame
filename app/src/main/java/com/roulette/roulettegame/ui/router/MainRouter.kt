package com.roulette.roulettegame.ui.router

import android.content.Intent
import com.roulette.roulettegame.mvp.contract.MainContract
import com.roulette.roulettegame.ui.activity.MyMvpAppCompatActivity
import com.roulette.roulettegame.ui.activity.WebActivity

class MainRouter(private val myMvpAppCompatActivity: MyMvpAppCompatActivity) :
    MainContract.Router {

    override fun showPrivacyPolicyScreen() {
        val intent = Intent(myMvpAppCompatActivity, WebActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        myMvpAppCompatActivity.startActivity(intent)
    }
}