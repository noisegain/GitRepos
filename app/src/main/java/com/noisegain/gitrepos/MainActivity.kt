package com.noisegain.gitrepos

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

val userset = arrayListOf(
    User("User1", arrayListOf(dataset[1], dataset[2])),
    User("User2", arrayListOf(dataset[0], dataset[3]))
)

class MainActivity : AppCompatActivity(), OnUserClickListener {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        favoriteButton.setOnClickListener {
            val myIntent = Intent(this, FavoriteActivity::class.java)
            startActivity(myIntent)
        }
    }

    private fun init() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(this@MainActivity)
        rcView.adapter = adapter
        adapter.refresh(userset)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val n = arrayListOf<User>()
                val toSearch = newText?.lowercase()?:""
                userset.forEach { x ->
                    if (toSearch in x.name.lowercase()) {
                        n.add(x)
                    }
                }
                adapter.refresh(n)
                return false
            }

        })
    }

    override fun onUserItemClicked(position: Int) {
        Toast.makeText(this, "WOW ${userset[position].name}", Toast.LENGTH_SHORT).show()
        val myIntent = Intent(this, MainActivity2::class.java)
        myIntent.putExtra("User", position)
        println(position)
        startActivity(myIntent)
    }
}