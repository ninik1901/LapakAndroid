package com.example.projek.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


/**
 * Created by Dhimas Panji Sastra on
 * Copyright (c)  . All rights reserved.
 * Last modified $file.lastModified
 * Made With ❤ for U
 */
class SessionManager @SuppressLint("CommitPrefEdits") constructor(context: Context) {
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    var context: Context
    var mode = 0

    companion object {
        const val key_iduser = "keyiduser"
        const val key_nohp = "nohp"
        const val key_nama = "keynama"
        private const val pref_name = "rumah_baca"
        private const val is_login = "islogin"

        private fun getSharedPreference(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

        //


        fun setLogin(context: Context, idLogin: Boolean) {
            val editor =
                getSharedPreference(context).edit()
            editor.putBoolean(is_login, idLogin)
            editor.apply()
        }

        fun is_login(context: Context): Boolean {
            return getSharedPreference(context)
                .getBoolean(is_login, false)
        }


        fun setidUser(context: Context, iduser: String?) {
            val editor =
                getSharedPreference(context).edit()
            editor.putString(key_iduser, iduser)
            editor.apply()
        }

        fun getIdUser(context: Context): String? {
            return getSharedPreference(context)
                .getString(key_iduser, "")
        }

        fun setKey_nohp(context: Context, nohp: String?) {
            val editor =
                getSharedPreference(context).edit()
            editor.putString(key_nohp, nohp)
            editor.apply()
        }

        fun getKey_nohp(context: Context): String? {
            return getSharedPreference(context)
                .getString(key_nohp, "")
        }

        fun setPref_name(context: Context, nama: String?) {
            val editor =
                getSharedPreference(context).edit()
            editor.putString(key_nama, nama)
            editor.apply()
        }

        fun getPref_name(context: Context): String? {
            return getSharedPreference(context)
                .getString(key_nama, "")
        }

    }

    init {
        this.context = context
        pref = context.getSharedPreferences(pref_name, mode)
        editor = pref.edit()
    }
}
