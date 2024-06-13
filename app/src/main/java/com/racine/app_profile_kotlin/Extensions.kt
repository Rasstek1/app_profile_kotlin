package com.racine.app_profile_kotlin

import android.net.Uri

fun String.toUri(): Uri {
    return Uri.parse(this)
}
