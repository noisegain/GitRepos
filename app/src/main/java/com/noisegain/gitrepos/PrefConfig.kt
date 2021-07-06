package com.noisegain.gitrepos

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.*
import java.io.StringReader
import java.net.URL

class PrefConfig {

    private val parser = Klaxon()
    fun writePref(fav: MutableSet<String>) {
        //val pref = PreferenceManager.getDefaultSharedPreferences()
        //val list = parser.toJsonString(fav.toList())
        //println(list)
        //with(pref.edit()) {
        //    putString("fav", list)
        //    apply()
        //}
    }
    fun readPref(): MutableSet<String> {
        //val pref = PreferenceManager.getDefaultSharedPreferences(this)
        //val json = pref.getString("fav", "")
        //val res = parser.parse<List<String>>(json?:"")
        //return res?.toMutableSet()?:mutableSetOf()
        return mutableSetOf("User1")
    }

    fun getUser(name: String): User {
        try {
            val req = URL("https://api.github.com/users/$name/repos").readText()
            println(req)
            val parsed = parser.parseJsonObject(StringReader(req))
            println(parsed)
        } finally {
            println("SSS")
        }
        return User("AAA", arrayListOf())
    }
}