package com.noisegain.gitrepos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL


class RepositoryActivity : AppCompatActivity() {

    private val adapter = ReposAdapter()

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val name = intent.getStringExtra("User")?:"noisegain"
        reposList.layoutManager = LinearLayoutManager(this)
        reposList.adapter = adapter
        GlobalScope.launch(Dispatchers.IO) {
            async { user = getUser(name) }
        }
    }

    fun getUser(name: String): User {
        val json = URL("https://api.github.com/users/$name/repos").readText()
        val res: ArrayList<RepositoryJSON> = Gson().fromJson(json, object: TypeToken<ArrayList<RepositoryJSON>>() {}.type)
        val repos = arrayListOf<Repository>()
        res.forEach {
            repos.add(Repository(it.name?:"", it.description?:"", it.language?:"", name))
        }
        runOnUiThread {
            title = name
            adapter.refresh(repos)
        }
        return User(name, repos)
    }
}