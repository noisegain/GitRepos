package com.noisegain.gitrepos

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.*
import com.noisegain.gitrepos.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

val dataset = arrayListOf(
    Repository("HelloWorld", "I'll be a next Hokage of this programming world", "Kotlin", "User2"),
    Repository("Cells", "I have got a new arm", "Cobol", "User1"),
    Repository("OldKey", "I want to know what are this cellar contains", "Kotlin", "User1"),
    Repository("Sea", "I'll kill all titans and go to the sea, witch contains salt", "Java", "User2")
)

val userset = mutableListOf(
    User("User1", arrayListOf(dataset[1], dataset[2])),
    User("User2", arrayListOf(dataset[0], dataset[3]))
)

var filtred_userset = userset.toMutableList()

var favorites = mutableSetOf<String>()

val prefConfig = PrefConfig()

class MainActivity : AppCompatActivity(), OnUserClickListener {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        val main_context = applicationContext
        favoriteButton.setOnClickListener {
            val myIntent = Intent(this, FavoriteActivity::class.java)
            startActivity(myIntent)
        }
    }

    private fun init() = with(binding) {
        favorites = prefConfig.readPref()
        rcView.layoutManager = LinearLayoutManager(this@MainActivity)
        rcView.adapter = adapter
        adapter.refresh(userset)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filtred_userset.clear()
                val toSearch = newText?.lowercase()?:""
                println(toSearch)
                userset.forEach { x ->
                    println(x.name.lowercase())
                    if (toSearch in x.name.lowercase()) {
                        filtred_userset.add(x)
                    }
                }
                println(filtred_userset)
                adapter.refresh(filtred_userset)
                return false
            }

        })
    }

    override fun onUserItemClicked(position: Int) {
        Toast.makeText(this, "WOW ${userset[position].name}", Toast.LENGTH_SHORT).show()
        val myIntent = Intent(this, MainActivity2::class.java)
        myIntent.putExtra("User", position)
        myIntent.putExtra("Main", true)
        println(position)
        startActivity(myIntent)
    }
}