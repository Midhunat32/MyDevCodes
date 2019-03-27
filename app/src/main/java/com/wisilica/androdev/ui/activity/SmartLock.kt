package com.wisilica.androdev.ui.activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.ConnectionResult
import kotlinx.android.synthetic.main.activity_smart_lock.*
import com.google.android.gms.auth.api.credentials.Credential
import android.widget.Toast
import com.google.android.gms.common.api.Status
import android.content.IntentSender
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.wisilica.androdev.R
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.auth.api.credentials.CredentialRequestResult
import com.google.android.gms.auth.api.credentials.CredentialRequest
import android.content.Intent
import android.os.Handler
import com.google.android.gms.common.api.ResultCallback
import java.lang.Exception


class SmartLock : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private val RC_SAVE = 1000
    private var mGoogleApiClient: GoogleApiClient? = null
    val TAG = "SmartLock"
    var mIsResolving: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_lock)

        initGoogleApi();
        registerListeners()

    }

    private fun registerListeners() {
        btnSign.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        when (v) {
            btnSign -> {
                getDataFromUi()
            }
        }
    }

    private fun initGoogleApi() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .enableAutoManage(this, 0, this)
            .addApi(Auth.CREDENTIALS_API)
            .build()
    }

    private fun getDataFromUi() {
        var username = editText.text.toString()
        var password = editText2.text.toString()
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Invalid Username", Toast.LENGTH_LONG).show()
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Invalid Password", Toast.LENGTH_LONG).show()
        }

        val credential = Credential.Builder(username)
            .setPassword(password)
            .build()

        saveCredentials(credential)

    }

    private fun saveCredentials(credential: Credential?) {
        Auth.CredentialsApi.save(mGoogleApiClient, credential).setResultCallback { status ->
            if (status.isSuccess) {
                moveToNext()
            } else {
                resolveResult(status, RC_SAVE)
            }
        }
    }


    private fun resolveResult(status: Status, requestCode: Int) {

        if (status.hasResolution()) {
            try {
                status.startResolutionForResult(this, requestCode)
            } catch (e: Exception) {
            }
        } else {
            if (requestCode === RC_SAVE) {
                Toast.makeText(this, "Save from resolution", Toast.LENGTH_LONG).show()
            }
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SAVE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
            }
            moveToNext()
        }
        mIsResolving = false
    }


    override fun onConnected(p0: Bundle?) {
        Log.d(TAG, "onConnected");
        Handler().postDelayed({
            requestCredentials()
        }, 1000)


    }

    override fun onConnectionSuspended(p0: Int) {
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    private fun requestCredentials() {

        val request = CredentialRequest.Builder()
            .setPasswordLoginSupported(true)
            .build()

        Auth.CredentialsApi.request(mGoogleApiClient, request).setResultCallback(
            object : ResultCallback<CredentialRequestResult> {
                override fun onResult(credentialRequestResult: CredentialRequestResult) {
                    val status = credentialRequestResult.status
                    if (credentialRequestResult.status.isSuccess) {
                        val credential = credentialRequestResult.credential
                        moveToNext();
                    } else if (status.statusCode == CommonStatusCodes.SIGN_IN_REQUIRED) {

                    }
                }
            }
        )
    }

    private fun moveToNext() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

}
