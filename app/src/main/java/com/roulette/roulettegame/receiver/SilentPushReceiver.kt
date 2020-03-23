package com.roulette.roulettegame.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.yandex.metrica.push.YandexMetricaPush




class SilentPushReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {

        val payload = intent.getStringExtra(YandexMetricaPush.EXTRA_PAYLOAD)
    }
}