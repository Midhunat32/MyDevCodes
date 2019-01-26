package com.wisilica.androdev.ui.activity.FbAuthentication

import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Base64
import android.util.Log
import com.facebook.*
import com.facebook.login.Login
import com.facebook.login.LoginResult
import com.wisilica.androdev.R
import com.wisilica.androdev.ui.activity.room.Devices
import kotlinx.android.synthetic.main.activity_fb_login.*
import java.security.MessageDigest
import java.util.*
import com.facebook.login.LoginManager
import org.json.JSONObject


class FbLogin : AppCompatActivity() {

    lateinit var callBack: CallbackManager
    lateinit var  progress:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fb_login)

        callBack = CallbackManager.Factory.create()
        if (AccessToken.getCurrentAccessToken()!=null){
            tvuserName.text=AccessToken.getCurrentAccessToken().source.name

        }


        LoginManager.getInstance().logInWithReadPermissions(this,
            Arrays.asList("public_profile","email","email", "user_birthday", "user_friends"));
//        btnFb.setPublishPermissions(Arrays.asList("public_profile","email"))
        btnFb.registerCallback(callBack, object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                }

                override fun onError(error: FacebookException?) {
                }

                override fun onSuccess(result: LoginResult?) {
                    progress =ProgressDialog(this@FbLogin)
                    progress.setMessage("Please wait")
                    progress.show()

                    var request:GraphRequest= GraphRequest.newMeRequest(result?.accessToken,
                        object :GraphRequest.GraphJSONObjectCallback{
                            override fun onCompleted(`objectfb`: JSONObject?, response: GraphResponse?) {
                                progress.dismiss()
                                getData(objectfb)


                            }
                        })
                    var parameters:Bundle= Bundle();
                    parameters.putString("public_profile","email")
                    request.parameters=parameters
                    request.executeAsync()




                }
            })


    }

    private fun getData(objectfb: JSONObject?) {
        var userName = objectfb?.get("name")
       // var email = objectfb?.get("email")
        //tvEmail.text=email.toString()
        tvuserName.text=userName.toString()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callBack.onActivityResult(requestCode,resultCode,data)

    }

    private fun printHasKey() {
        var pakageInfo = packageManager.getPackageInfo("com.wisilica.androdev", PackageManager.GET_SIGNING_CERTIFICATES)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            for (signature in pakageInfo.signingInfo.signingCertificateHistory) {
                var messageDigest = MessageDigest.getInstance("SHA")
                messageDigest.update(signature.toByteArray())
                Log.d("VVV", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT))
            }
        } else {
            var pakageInfo = packageManager.getPackageInfo("com.wisilica.androdev", PackageManager.GET_SIGNATURES)
            for (signature in pakageInfo.signatures) {
                var messageDigest = MessageDigest.getInstance("SHA")
                messageDigest.update(signature.toByteArray())
                Log.d("VVV", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT))
            }
        }
    }
}
