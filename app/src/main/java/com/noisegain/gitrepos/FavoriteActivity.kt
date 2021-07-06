package com.noisegain.gitrepos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity(), OnUserClickListener {

    private val adapter = UsersAdapter(this)

    private var users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        title = "Favorites"
        rcFav.layoutManager = LinearLayoutManager(this)
        rcFav.adapter = adapter
        favorites.forEach {
            users.add(User("AAA", arrayListOf(Repository("A", "B"), Repository("C", "D"))))
        }
        adapter.refresh(users)
    }

    override fun onUserItemClicked(position: Int) {
        Toast.makeText(this, "WOW ${userset[position].name}", Toast.LENGTH_SHORT).show()
        val myIntent = Intent(this, MainActivity2::class.java)
        myIntent.putExtra("User", position)
        myIntent.putExtra("Main", false)
        myIntent.putExtra("Fav", users[position].name)
        println(position)
        startActivity(myIntent)
    }
}