package com.noisegain.gitrepos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.beust.klaxon.Klaxon
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.noisegain.gitrepos.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.net.URL

val userset = mutableListOf<User>()

var favorites = mutableSetOf<String>()

var favChanged = false

class MainActivity : AppCompatActivity(), OnUserClickListener {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter(this)

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = applicationContext
        init()
        val main_context = applicationContext
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
            var json = " "
            var err = 0
            while (
                try {
                    json = URL("https://api.github.com/search/users?q=$name").readText()
                    println(json)
                    false
                } catch (e: FileNotFoundException) {
                    true
                }
            ) {
                println("ERROR $err")
                err++
                if (err == 5) return
            }
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
        Toast.makeText(this, "WOW ${userset[position].name}", Toast.LENGTH_SHORT).show()
        val myIntent = Intent(this, RepositoryActivity::class.java)
        myIntent.putExtra("User", userset[position].name)
        startActivity(myIntent)
    }

    override fun writePref() {
        println("WRITING")
        val pref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        println(favorites)
        val list = Klaxon().toJsonString(favorites.toList())
        Log.d("AAAAA", list)
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