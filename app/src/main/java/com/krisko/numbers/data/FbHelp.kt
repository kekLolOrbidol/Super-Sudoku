package com.krisko.numbers.data

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import com.facebook.applinks.AppLinkData
import com.krisko.numbers.api.WebApi
import com.ticitaki.rozkiapp.Msg

class FbHelp(val context: Context) {
    var url : String? = null
    var mainActivity : WebApi? = null
    val sPrefUrl = SPref(context).apply { getSp("fb") }

    init{
        url = sPrefUrl.getStr("url")
        if(url == null) tree()
    }

    fun attachWeb(api : WebApi){
        mainActivity = api
    }

    private fun tree() {
        AppLinkData.fetchDeferredAppLinkData(context
        ) { appLinkData: AppLinkData? ->
            if (appLinkData != null && appLinkData.targetUri != null) {
                if (appLinkData.argumentBundle["target_url"] != null) {
                    Msg().messageSchedule(context)
                    Log.e("DEEP", "SRABOTAL")
                    val tree = appLinkData.argumentBundle["target_url"].toString()
                    val uri = tree.split("$")
                    url = "https://" + uri[1]
                    if(url != null){
                        sPrefUrl.putStr("url", url!!)
                        mainActivity?.openWeb(url!!)
                    }
                }
            }
        }
    }
}