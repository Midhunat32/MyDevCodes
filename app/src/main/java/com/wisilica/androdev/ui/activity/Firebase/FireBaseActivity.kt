package com.wisilica.androdev.ui.activity.Firebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.wisilica.androdev.R
import com.google.firebase.iid.InstanceIdResult
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.iid.FirebaseInstanceId


class FireBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_base)

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this@FireBaseActivity) {
                instanceIdResult ->
            val newToken = instanceIdResult.token
        }


    }
}
