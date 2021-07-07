package com.noisegain.gitrepos

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.*
import java.io.StringReader
import java.net.URL

interface Writer {
    fun writePref(fav: MutableSet<String>)
    fun readPref(): MutableSet<String>
    fun getUser(name: String): User
}