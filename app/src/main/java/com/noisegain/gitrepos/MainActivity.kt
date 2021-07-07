package com.noisegain.gitrepos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.*
import com.beust.klaxon.Klaxon
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.noisegain.gitrepos.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.net.URL

val userset = mutableListOf<User>()

var favorites = mutableSetOf<String>()

var favChanged = false

interface Writer {
    fun writePref(fav: MutableSet<String>)
    fun readPref(): MutableSet<String>
    fun getUser(name: String): User
}

class MainActivity : AppCompatActivity(), OnUserClickListener {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        favoriteButton.setOnClickListener {
            val myIntent = Intent(this, FavoriteActivity::class.java)
            startActivity(myIntent)
        }
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                GlobalScope.launch(Dispatchers.IO) {
                    async { searchUsers(query) }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun onRestart() {
        super.onRestart()
        if (favChanged) writePref()
        favChanged = false
        init()
    }

    private fun init() = with(binding) {
        favorites = readPref()
        userset.clear()
        favorites.forEach {
            userset.add(User(it, arrayListOf()))
        }
        rcView.layoutManager = LinearLayoutManager(this@MainActivity)
        rcView.adapter = adapter
        adapter.refresh(userset)
    }

    fun searchUsers(name: String?) {
        name?:return
        userset.clear()
        if (name == "") {
            favorites.forEach {
                userset.add(User(it, arrayListOf()))
            }
        } else {
            val json = URL("https://api.github.com/search/users?q=$name").readText()
            val res: UserSearchJSON = Gson().fromJson(json, object: TypeToken<UserSearchJSON>() {}.type)
            res.items?.forEach {
                userset.add(User(it.login, arrayListOf()))
            }
        }
        runOnUiThread {
            adapter.refresh(userset)
        }
    }

    override fun onUserItemClicked(position: Int) {
        val myIntent = Intent(this, RepositoryActivity::class.java)
        myIntent.putExtra("User", userset[position].name)
        startActivity(myIntent)
    }

    override fun writePref() {
        println("WRITING")
        val pref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val list = Klaxon().toJsonString(favorites.toList())
        with(pref.edit()) {
            putString("fav", list)
            apply()
        }
    }

    private fun readPref(): MutableSet<String> {
        val pref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val json = pref.getString("fav", null) ?: return mutableSetOf()
        val res = Klaxon().parseArray<String>(json)
        return res?.toMutableSet()?:mutableSetOf()
    }
}