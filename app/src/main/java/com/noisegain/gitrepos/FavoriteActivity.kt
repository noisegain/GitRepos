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
            users.add(User(it, arrayListOf()))
        }
        adapter.refresh(users)
        favClearButton.setOnClickListener {
            favorites.clear()
            adapter.refresh(mutableListOf())
        }
    }

    override fun onUserItemClicked(position: Int) {
        val myIntent = Intent(this, RepositoryActivity::class.java)
        myIntent.putExtra("User", users[position].name)
        startActivity(myIntent)
    }

    override fun writePref() { favChanged = true }
}